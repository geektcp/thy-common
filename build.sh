#!/bin/sh

date && mvn clean deploy -P release,gpg && date