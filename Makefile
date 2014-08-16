all: download-dependencies

.PHONY: all download-dependencies

download-dependencies: libs/jsevaluator.jar assets/javascript-libs/suncalc assets/javascript-libs/opening_hours

assets/javascript-libs/opening_hours:
	npm install suncalc

assets/javascript-libs/suncalc:
	npm install suncalc

libs/jsevaluator.jar:
	wget 'https://github.com/evgenyneu/js-evaluator-for-android/raw/master/JSEvaluator/bin/jsevaluator.jar' -O 'libs/jsevaluator.jar'
