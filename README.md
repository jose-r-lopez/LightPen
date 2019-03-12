# LightPen
Lightpen mobile pentesting tool V1.1

## Description

Lightpen is a lightweight mobile pentesting tool enabling several security scanning activities from any
Android device. It does not need root permissions, and all the scanning activities are aimed to offer
agile responses and low disruption to the target. Lightpen is being used to gather information to remote 
systems at any place so we can quickly check if these systems are very vulnerable or as a previous step
to perform a full security audit so we can gather information that may enable us to better design the 
audit activities, saving resources and time. 

Currently, Lightpen implements the following plugins. Each plugin is a different pentesting activity:

* **HTTP Headers**: Analyze HTTP headers to check if some security-related headers have been activated (HSTS, HKPK, XSS protections...). 
* **Robots.txt**: Checks if there is a robots.txt file and analyzes their contents to check if it may facilitate other attacks. 
* **URL Mapper**: Creates an URL map and allows us to view URLs to check that undesired URL are not being referred. 
* **HTTP Methods**: Analyzes the HTTP Methods supported by a server, checking that no dangerous method is supported. 
* **Ports**: Analyzes open TCP and UDP ports in a server, identifying the running services, in case they may suppose a security problem. 
* **Error Page**: Checks if there are default error pages providing too much information about the remote system. 
* **Dir Listing**: Checks if a server has directory listings enabled, giving too much information about their contents. 
* **Comments**: Locate and report different types of comments in the web page to avoid offering too much information about the system. 
* **Device Locator**: Scans and identifies devices in the remote system subnet using the ARP cache.

This is an ongoing research project to test the feasibility of a portable auditing system, and therefore
is still under development. 

## Sources and binaries

The last stable version of the source code and its binaries are available in the [releases](https://github.com/jose-r-lopez/LightPen/releases) section.

To import the project in Android Studio, just download the sources and unzip them in any folder you like. Then, you just have to use the "Import project" option
and locate the folder "Lightpen-master" to import it. Android Studio sometimes gives problems with long routes or with folder permisssions, therefore is 
recommendable to use a suitable route to unpack the sources.

Testing from Android Studio have been done with an Emulated Google Pixel XL (Android 7.0, API 24). Newer versions should work without problems.

## More info

See [javadoc](https://jose-r-lopez.github.io/LightPen/) of the current version of the project


## Author

Iñigo Llaneza Aller, Jose Manuel Redondo López
