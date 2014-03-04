/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

/**
* 	This class lets to start the speech recognition process
*/
var cordova= 	require('cordova'),
	exec= 		require('cordova/exec');

var SpeechRecognizer= function(){
};

/**
* 	Method which start the speech recognition process
*
*	@param {Function} successCallback	function which will receive the results of the recognition process
*	@param {Function} errorCallbakc		function which will receive the error during the recognitionc process
*	@param {Integer} maxResults		integer with the maximum number of results that the service has to provide
*     @param {String} promptMessage         string with the prompt message to show through the user interface
*/
SpeechRecognizer.prototype.recognize= function(successCallback, errorCallback, maxResults, promptMessage){
	return cordova.exec(successCallback, errorCallback, "SpeechRecognizer", "recognize", [maxResults, promptMessage]);
};

var speechRecognizer= new SpeechRecognizer();
module.exports= speechRecognizer;