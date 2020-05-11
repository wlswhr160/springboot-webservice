#!/usr/bin/env bash

# 쉬고 있는 프로파일 찾기
function find_idle_profile_old()
{
	RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)
	
	if [ ${RESPONSE_CODE} -ge 400 ]
 	then
 		CURRENT_PROFILE=real2
 	else
 		CURRENT_PROFILE=real1
 	fi
 	
 	if [ ${CURRENT_PROFILE} == real1 ]
 	then
 		IDLE_PROFILE=real2
 	else
 		IDLE_PROFILE=real1
 	fi
 	
 	echo "${IDLE_PROFILE}"
}

# 쉬고 있는 포트 검색
function find_idle_port_old()
{
	IDLE_PROFILE=$(find_idle_profile)
	
	if [ ${IDLE_PROFILE} == real1 ]
	then
		echo "8081"
	else
		echo "8082"
	fi
}

function find_idle_port {	
	PORT=$(cat /etc/nginx/conf.d/service-url.inc | grep 8081)
	if [ $PORT == 8081 ] 
	then 
	    echo "8082"
	else 
	    echo "8081"
	fi
}

function find_idle_profile() {
	IDLE_PORT=$(find_idle_port)
	if [ $IDLE_PORT == 8081 ]
	then
		echo "real1"
	else 
		echo "real2"
	fi	
}
