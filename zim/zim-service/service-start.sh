#!/usr/bin/env bash

ip=$(ifconfig | grep -A 1 'eth0' | grep 'inet' | sed 's/^.*inet //g' | cut -d ' ' -f 1 | sed 's/^addr://g')

