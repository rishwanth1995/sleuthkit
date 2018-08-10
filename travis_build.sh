#!/bin/sh
set -ex
installLib() {
	git clone https://github.com/libyal/$1
	cd $1
	git checkout tags/$2
	./synclibs.sh
	./autogen.sh
	if test ${TRAVIS_OS_NAME} = "linux"; then
		./configure -prefix=/usr > /dev/null
	else
		./configure > /dev/null
	fi 
	make > /dev/null && sudo make install > /dev/null
	cd ..
}

if test ${TRAVIS_OS_NAME} = "linux"; then
	sudo apt-get -qq update
	sudo apt-get -y install libafflib-dev libewf-dev libpq-dev autopoint libsqlite3-dev ant libcppunit-dev
elif test ${TRAVIS_OS_NAME} = "osx"; then
	export PATH=${PATH}:/usr/local/opt/gettext/bin
	brew install ant libewf gettext cppunit
fi
installLib libvhdi 20160424
installLib libvmdk 20160119
./bootstrap && ./configure --prefix=/usr && make > /dev/null
cd bindings/java/ && ant -q dist-PostgreSQL
