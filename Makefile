all: download-dependencies

.PHONY: all download-dependencies

download-dependencies: libs/jsevaluator.jar assets/javascript-libs/suncalc
	$(MAKE) -C "assets/javascript-libs"

assets/javascript-libs/suncalc:
	npm install suncalc

libs/jsevaluator.jar:
	wget 'https://github.com/evgenyneu/js-evaluator-for-android/raw/master/JSEvaluator/bin/jsevaluator.jar' -O 'libs/jsevaluator.jar'
