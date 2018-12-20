# Pro-C-for-Eclipse: Pro*C plugin for Eclipse.

This repository contains a complete Eclipse workspace to compile successfully the Pro*C plugin.
I don't code in Java, but you can send pull requests to make this plugin better.

Original work by https://github.com/buntatsu/cdt-proc. The firsts commits are, indeed, the original repo.

## How to compile the plugin

🔘 You will need a **computer** with **JDK** installed. I suggest a list I tested it and it works. You can simply ignore it and use the most recent one. I tested on Windows, but you can use a osX or the Linux flavor you want.

🔘 Download from https://www.eclipse.org/downloads/packages/release the package you want. Ensure you choose the **Eclipse IDE for Eclipse Committers** version.

🔘 Select the branch and download the workspace inside the branch repo into your computer.

🔘 Open your **Eclipse IDE for Eclipse Committers** and open the workspace.

🔘 Select in any project the export option -> Deployable plugins and fragments -> select all -> Directory -> Select the directory -> Click Finish.

🔘 Wait until completion and now you have your plugin ready in the folder you provided!

## How to use the compiled plugin

⬜️ Download from https://www.eclipse.org/downloads/packages/release the package you want and install it. You will need the **Eclipse IDE for C/C++ Developers** version

⬜️ When you finish the installation, go to the *plugins* folder and find a file starts like this: org.eclipse.cdt_X.Y.Z.YYYYMMDDhhmm.jar. X.Y.Z stands for the version. Annotate it.

⬜️ Go here and download the release with that version, if I have it.

⬜️ If I don't have it, submit an issue.

## TODO

I hope I will compile the plugin from CDT 9.4.0 for Eclipse Oxygen until the last release. Time is my enemy!

‼️ Versions to be done:

❌️ CDT_9_6_0

❌️ CDT_9_5_5

❌️ CDT_9_5_4

✔️ CDT_9_5_3

❌️ CDT_9_5_2

❌️ CDT_9_5_1

❌️ CDT_9_5_0

❌️ CDT_9_4_3

❌️ CDT_9_4_2

❌️ CDT_9_4_1

❌️ CDT_9_4_0

## JDK Used:
> **Eclipse CDT 9.5.3:** JDK 1.8
