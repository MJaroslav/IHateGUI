#!/bin/sh
xvfb-run -e /dev/stdout -s "-screen 0 1280x1024x24 -ac -nolisten tcp -nolisten unix" -a $@
