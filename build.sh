#!/bin/sh

git pull
date && mvn clean deploy -P release,gpg && date