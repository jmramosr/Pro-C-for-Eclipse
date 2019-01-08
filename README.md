# Pro-C-for-Eclipse: Pro*C plugin for Eclipse.

This repository contains a complete Eclipse workspace to compile successfully the Pro*C plugin.
I don't code in Java, but you can send pull requests to make this plugin better.

Original work by https://github.com/buntatsu/cdt-proc. The firsts commits are, indeed, the original repo.

## How to compile the plugin

🔘 You will need a **computer** with **JDK** installed. I suggest a list I tested it and it works. You can simply ignore it and use the most recent **JDK**. I tested on Windows, but you can use a osX or the Linux flavor you want.

🔘 Download from https://www.eclipse.org/downloads/packages/release the package you want. Ensure you choose the **Eclipse IDE for Eclipse Committers** version.

🔘 Open your **Eclipse IDE for Eclipse Committers**, create an empty workspace and install the version of CDT you want to use. You must install, at least, the main features. Restart the program.

🔘 Go here and select the branch which name contains the name of your CDT version. 

🔘 Download the branch. It contains a workspace inside the branch.

🔘 Open your **Eclipse IDE for Eclipse Committers** and open the workspace. Wait until completes the building of the workspace. It probably doesn't have the auto build.

🔘 Select in any project the export option -> Deployable plugins and fragments -> select all -> Directory -> Select the directory -> Click Finish.

🔘 Wait until completion and now you have your plugin ready in the folder you provided!

## How to use the compiled plugin

🔘 Download from https://www.eclipse.org/downloads/packages/release the package you want and install it. You will need the **Eclipse IDE for C/C++ Developers** version

🔘 When you finish the installation, go to the *plugins* folder and find a file starts like this: _org.eclipse.cdt.core_X.Y.Z.YYYYMMDDhhmm.jar_. X.Y.Z stands for the version. Annotate the date.

🔘 Go here and download the release with that version, if I have it.

🔘 If I don't have it, submit an issue. Submit the name of your _org.eclipse.cdt_X.Y.Z.YYYYMMDDhhmm.jar_ file, not the _org.eclipse.cdt.core_X.Y.Z.YYYYMMDDhhmm.jar_ one!

🔘 Replace the _org.eclipse.cdt.core_X.Y.Z.YYYYMMDDhhmm.jar_ and copy the other ones. Backup first the _org.eclipse.cdt.core_X.Y.Z.YYYYMMDDhhmm.jar_ file if you don't feel confident.

## How to upgrade the plugin with Eclipse CDT

🔘 Update CDT to the release version you want. This step assumes you have the version **Eclipse IDE for C/C++ Developers** for your package. If you have been installed a previous Pro*C plugin for Eclipse in this version, restore the original _org.eclipse.cdt.core_X.Y.Z.YYYYMMDDhhmm.jar_.

🔘 Search in your plugins folder the lastest _org.eclipse.cdt.core_X.Y.Z.YYYYMMDDhhmm.jar_. You will have, at least, 2 versions of the file _org.eclipse.cdt.core_X.Y.Z.YYYYMMDDhhmm.jar_.

🔘 Search in the releases list the lastest CDT version you have and download it.

🔘 Replace the _org.eclipse.cdt.core_X.Y.Z.YYYYMMDDhhmm.jar_ and copy the other ones. Backup first the _org.eclipse.cdt.core_X.Y.Z.YYYYMMDDhhmm.jar_ file if you don't feel confident.

## Version List

| Package   | Compilated package numbers             |
|-----------|----------------------------------------|
| CDT_9_6_0 | org.eclipse.cdt_9.6.0.201811241055.jar |
| CDT_9_5_5 | org.eclipse.cdt_9.5.5.201811180605.jar |
| CDT_9_5_4 | org.eclipse.cdt_9.5.4.201810050005.jar |
| CDT_9_5_3 | org.eclipse.cdt_9.5.3.201809121146.jar |
| CDT_9_5_2 | org.eclipse.cdt_9.5.2.201807181141.jar |
| CDT_9_5_1 | org.eclipse.cdt_9.5.1.201807051742.jar |
| CDT_9_5_0 | org.eclipse.cdt_9.5.0.201806170908.jar |
| CDT_9_4_3 | org.eclipse.cdt_9.4.3.201802261533.jar |
| CDT_9_4_2 | org.eclipse.cdt_9.4.2.201802122019.jar |
| CDT_9_4_1 | org.eclipse.cdt_9.3.0.201801130900.jar |
| CDT_9_4_0 | org.eclipse.cdt_9.3.0.201712020452.jar |

## Warnings

⬜️ Be careful about CDT_9_4_1 and CDT_9_4_0! They use the same names with different dates. That's why you can annotate the release date, it serves as reference number for me.

## TODO

✔️ Get the version numbers of each package and make the version list.

✔️ Renaming the original plugin.

❌️ Upload every version.

❌️ Buglist of everyday use.

❌️ Not modifying the original _org.eclipse.cdt.core_X.Y.Z.YYYYMMDDhhmm.jar_ to use this plugin.

## JDK Used:

All CDT versions uses JDK 1.8 to compile them, as you can see in the manifest.mf files in each version.
