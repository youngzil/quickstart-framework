#!/bin/bash

cd $HOME/lims-0.0.1-SNAPSHOT
sh bin/shutdown.sh

cd $HOME
rm -rf lims-0.0.1-SNAPSHOT
tar -xzvf lims-0.0.1-SNAPSHOT.tar.gz

cd $HOME/lims-0.0.1-SNAPSHOT
sh bin/startup.sh
