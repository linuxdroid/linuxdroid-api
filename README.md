Linuxdroid API
==========
[![Join the chat at https://gitter.im/linuxdroid/linuxdroid](https://badges.gitter.im/linuxdroid/linuxdroid.svg)](https://gitter.im/linuxdroid/linuxdroid)

This is an app exposing Android API to command line usage and scripts or programs.

- [Linuxdroid:API on Google Play](https://play.google.com/store/apps/details?id=com.linuxdroid.api)

When developing or packaging, note that this app needs to be signed with the same key as the main Linuxdroid app for permissions to work (only the main Linuxdroid app are allowed to call the API methods in this app).

License
=======
Released under the [GPLv3 license](http://www.gnu.org/licenses/gpl-3.0.en.html).

How API calls are made through the linuxdroid-api helper binary
===========================================================
The [linuxdroid-api](https://github.com/linuxdroid/linuxdroid-api-package/blob/master/linuxdroid-api.c) client binary in the `linuxdroid-api` package generates two linux anonymous namespace sockets, and passes their address to the [LinuxdroidApiReceiver broadcast receiver](https://github.com/linuxdroid/linuxdroid-api/blob/master/app/src/main/java/com/linuxdroid/api/LinuxdroidApiReceiver.java) as in:
	
	/system/bin/am broadcast ${BROADCAST_RECEIVER} --es socket_input ${INPUT_SOCKET} --es socket_output ${OUTPUT_SOCKET}

The two sockets are used to forward stdin from `linuxdroid-api` to the relevant API class and output from the API class to the stdout of `linuxdroid-api`.

Client scripts
==============
Client scripts which processes command line arguments before calling the `linuxdroid-api` helper binary are available in [the linuxdroid-api package](https://github.com/linuxdroid/linuxdroid-api-package).

Ideas
=====
- Wifi network search and connect.
- Add extra permissions to the app to (un)install apps, stop processes etc.
