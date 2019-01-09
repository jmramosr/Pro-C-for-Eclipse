package org.eclipse.cdt.internal.core.parser.scanner;

import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IFileNomination;
import org.eclipse.cdt.core.index.IIndexFile;
import org.eclipse.cdt.core.parser.ISignificantMacros;
import org.eclipse.cdt.internal.core.dom.parser.ASTNodeSpecification;
import org.eclipse.core.runtime.CoreException;

import java.util.ArrayList;
import java.util.Objects;

import org.eclipse.cdt.core.dom.IName;
import org.eclipse.cdt.core.dom.ast.ASTNodeProperty;
import org.eclipse.cdt.core.dom.ast.IASTComment;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTFunctionStyleMacroParameter;
import org.eclipse.cdt.core.dom.ast.IASTImageLocation;
import org.eclipse.cdt.core.dom.ast.IASTMacroExpansionLocation;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTNodeLocation;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorElifStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorElseStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorEndifStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorErrorStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorFunctionStyleMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIfStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIfdefStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIfndefStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorMacroExpansion;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorObjectStyleMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorPragmaStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorUndefStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit.IDependencyTree;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit.IDependencyTree.IASTInclusionNode;
import org.eclipse.cdt.core.dom.ast.IBinding;
import org.eclipse.cdt.core.dom.ast.IFileNomination;
import org.eclipse.cdt.core.dom.ast.IMacroBinding;
import org.eclipse.cdt.core.index.IIndexFile;
import org.eclipse.cdt.core.parser.ISignificantMacros;
import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.core.parser.util.CharArrayUtils;
import org.eclipse.cdt.internal.core.dom.parser.ASTNode;
import org.eclipse.cdt.internal.core.dom.parser.ASTNodeSpecification;
import org.eclipse.core.runtime.CoreException;


public class ASTInclusionStatement extends ASTPreprocessorNode implements IASTPreprocessorIncludeStatement {
	private static final ISignificantMacros[] NO_VERSIONS = {};
	
	private final ASTPreprocessorName fName;
	private final String fPath;
	private final boolean fIsResolved;
	private final boolean fIsSystemInclude;
	private final boolean fFoundByHeuristics;
	private final boolean fIncludedFileExported;
	private final IFileNomination fNominationDelegate;
	private boolean fPragmaOnce;
	private boolean fCreatesAST;
	private ISignificantMacros fSignificantMacros;
	private ISignificantMacros[] fLoadedVersions = NO_VERSIONS;
	private long fIncludedFileContentsHash;
	private long fIncludedFileTimestamp = -1;
	private long fIncludedFileSize;
	private long fIncludedFileReadTime;
	private boolean fErrorInIncludedFile;

	public ASTInclusionStatement(IASTTranslationUnit parent, 
			int startNumber, int nameStartNumber, int nameEndNumber, int endNumber,
			char[] headerName, String filePath, boolean userInclude, boolean active, boolean heuristic, 
			boolean exportedFile, IFileNomination nominationDelegate) {
		super(parent, IASTTranslationUnit.PREPROCESSOR_STATEMENT, startNumber, endNumber);
		fName= new ASTPreprocessorName(this, IASTPreprocessorIncludeStatement.INCLUDE_NAME,
				nameStartNumber, nameEndNumber, headerName, null);
		fPath= filePath == null ? "" : filePath; //$NON-NLS-1$
		fIsResolved= filePath != null;
		fIsSystemInclude= !userInclude;
		fFoundByHeuristics= heuristic;
		fSignificantMacros= ISignificantMacros.NONE;
		fNominationDelegate= nominationDelegate;
		fIncludedFileExported= exportedFile;
		if (!active) {
			setInactive();
		}
	}

	@Override
	public IASTName getName() {
		return fName;
	}

	@Override
	public String getPath() {
		return fPath;
	}

	@Override
	public boolean isResolved() {
		return fIsResolved;
	}

	@Override
	public boolean isSystemInclude() {
		return fIsSystemInclude;
	}
	
	@Override
	void findNode(ASTNodeSpecification<?> nodeSpec) {
		super.findNode(nodeSpec);
		nodeSpec.visit(fName);
	}

	@Override
	public boolean isResolvedByHeuristics() {
		return fFoundByHeuristics;
	}

	@Override
	public boolean hasPragmaOnceSemantics() {
		if (fNominationDelegate != null) {
			try {
				return fNominationDelegate.hasPragmaOnceSemantics();
			} catch (CoreException e) {
			}
		} 	
		return fPragmaOnce;
	}
	
	public void setPragamOnceSemantics(boolean value) {
		assert fNominationDelegate == null;
		fPragmaOnce= value;
	}

	@Override
	public ISignificantMacros getSignificantMacros() {
		if (fNominationDelegate != null) {
			try {
				return fNominationDelegate.getSignificantMacros();
			} catch (CoreException e) {
			}
		} 	
		return fSignificantMacros;
	}
	
	public void setSignificantMacros(ISignificantMacros sig) {
		assert sig != null;
		assert fNominationDelegate == null;
		fSignificantMacros= sig;
	}

	public void setLoadedVersions(ISignificantMacros[] versions) {
		fLoadedVersions= versions;
	}

	@Override
	public ISignificantMacros[] getLoadedVersions() {
		return fLoadedVersions;
	}
	
	@Override
	public long getIncludedFileTimestamp() {
		if (fNominationDelegate != null) {
			return 0;
		} 	
		return fIncludedFileTimestamp;
	}
	
	public void setIncludedFileTimestamp(long timestamp) {
		assert fNominationDelegate == null;
		fIncludedFileTimestamp= timestamp;
	}

	@Override
	public long getIncludedFileReadTime() {
		if (fNominationDelegate != null) {
			return 0;
		} 	
		return fIncludedFileReadTime;
	}

	public void setIncludedFileReadTime(long time) {
		assert fNominationDelegate == null;
		fIncludedFileReadTime= time;
	}

	@Override
	public long getIncludedFileSize() {
		if (fNominationDelegate != null) {
			return 0;
		} 	
		return fIncludedFileSize;
	}
	
	public void setIncludedFileSize(long size) {
		assert fNominationDelegate == null;
		fIncludedFileSize= size;
	}

	@Override
	public long getIncludedFileContentsHash() {
		if (fNominationDelegate != null) {
			return 0;
		} 	
		return fIncludedFileContentsHash;
	}
	
	public void setIncludedFileContentsHash(long hash) {
		assert fNominationDelegate == null;
		fCreatesAST= true;
		fIncludedFileContentsHash= hash;
	}

	@Override
	public boolean isErrorInIncludedFile() {
		if (fNominationDelegate != null) {
			return false;
		} 	
		return fErrorInIncludedFile;
	}
	
	public void setErrorInIncludedFile(boolean error) {
		assert fNominationDelegate == null;
		fErrorInIncludedFile= error;
	}

	@Override
	public boolean isIncludedFileExported() {
		return fIncludedFileExported;
	}

	@Override
	public boolean createsAST() {
		return fCreatesAST;
	}
	
	@Override
	public IIndexFile getImportedIndexFile() {
		if (fNominationDelegate instanceof IIndexFile)
			return (IIndexFile) fNominationDelegate;
		
		return null;
	}
}
