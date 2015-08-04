#!/bin/sh

tree=$1
path=$2

git remote add $tree $path
git fetch $tree
git merge -s ours --no-commit $tree/master
git read-tree --prefix=$tree/ -u $tree/master
gitk --all
