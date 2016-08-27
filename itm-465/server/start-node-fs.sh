#!/bin/bash
# ---------------------------------------------------------------------------------------------------|
#  School / Organization   : Illinois Institute of Technology________________________________________|
#  Class                   : ITM465__________________________________________________________________|
#  Specification           : N/A_____________________________________________________________________|
#  Specification Path      : N/A_____________________________________________________________________|
#  Author                  : brady house_____________________________________________________________|
#  Create date             : 02/11/2015______________________________________________________________|
#  Description             : UTILITY USED TO STARTUP A NODE.JS FILE SERVER SCRIPT USING A SECONDARY__|
#                          : THREAD._________________________________________________________________|
#  Command line Arguments  : N/A_____________________________________________________________________|
# ---------------------------------------------------------------------------------------------------|
#  Revision History::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::|
# ---------------------------------------------------------------------------------------------------|
# Baseline Ver.
# ---------------------------------------------------------------------------------------------------|

_path=$(pwd;)  # Capture Path

#try
(
  # Validate requisites
  node --version || exit 86
  if [[ ! -f ${_path}/stop-node-fs.sh ]]; then exit 87; fi
  if [[ ! -f ${_path}/node-fs.js ]]; then exit 88; fi
  if [[ ! -d ${_path}/www ]]; then exit 89; fi

  # Kill existing process
  ./stop-node-fs.sh || exit 90

  # Change directory to server root
  cd ${_path}/www

  # Startup on a secondary thread
  nohup node ${_path}/node-fs.js &

)
#catch
_rc=$?
case ${_rc} in
    0)  echo "node file server started @ http://localhost:8888"
        ;;
    86) echo "Please install node.js"
        ;;
    87) echo "FUBAR ~ ${_path}/stop-node-fs.sh could not be found."
        ;;
    88) echo "FUBAR ~ ${_path}/node-fs.js script could not be found."
        ;;
    89) echo "FUBAR ~ ${_path}/server/www directory does not exist."
        ;;
    90) echo "FUBAR ~ Attempt to execute ${_path}/stop-node-fs.sh failed."
        ;;
    *)  echo "An unknown error has occurred. Haaa-Ha! You Win :)"
        ;;
esac
#bubble up (re-throw)
exit ${_rc}