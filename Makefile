SHELL := /bin/bash

all: download-dependencies

.PHONY: all download-dependencies

download-dependencies: libs/jsevaluator.jar assets/javascript-libs/suncalc/suncalc.min.js assets/javascript-libs/opening_hours

assets/javascript-libs/opening_hours:
	npm install opening_hours

assets/javascript-libs/suncalc/suncalc.js:
	npm install suncalc

assets/javascript-libs/suncalc/suncalc.min.js: assets/javascript-libs/suncalc/suncalc.js
	uglifyjs "$<" --output "$@" --comments '/mourner\/suncalc/' --lint

libs/jsevaluator.jar:
	wget 'https://github.com/evgenyneu/js-evaluator-for-android/raw/master/JSEvaluator/bin/jsevaluator.jar' -O 'libs/jsevaluator.jar'

.SILENT: getStringsFiles
.PHONY:  getStringsFiles
getStringsFiles:
	rsync ../Alarm_Klock/android/alarmclock/res/ res --include='*/' --include='strings.xml' --include='arrays.xml' --exclude='*' -va
