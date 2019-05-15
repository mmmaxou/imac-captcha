#!/bin/bash

rsync -avz --exclude '*.java' ./src/ ./bin/ && find . -name "*.java" -print | xargs javac -d bin && java -cp bin fr.upem.captcha.Main