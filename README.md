<!---
	Licensed to the Apache Software Foundation (ASF) under one
	or more contributor license agreements.  See the NOTICE file
	distributed with this work for additional information
	regarding copyright ownership.  The ASF licenses this file
	to you under the Apache License, Version 2.0 (the
	"License"); you may not use this file except in compliance
	with the License.  You may obtain a copy of the License at

	  http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing,
	software distributed under the License is distributed on an
	"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
	KIND, either express or implied.  See the License for the
	specific language governing permissions and limitations
	under the License.
-->

cordova-plugin-speech-recognizer
================================

Cordova plugin which will provide a speech recognition service for PhoneGap applications

## Installation

cordova plugin add https://github.com/manueldeveloper/cordova-plugin-speech-recognizer.git

## Use

You can access to this plugin through `navigator.speechrecognizer` and then, you can call to the `recognize` method which has the following parameters:

- __successCallback__: Callback which will be called when the recognition service ends providing the results as _(JSONArray)_
- __failCallback__: Callback which will be called when the recognition service detects a problem with its code error as _(String)_
- __maxResults__: _(Integer)_ with the maximum number of results to provide
- __promptMessage__: _(String)_ with a minimum title to show through the user interface

### Supported Platforms

- Android
- iOS (in developing process)

### Example

	navigator.speechrecognizer.recognize(successCallback, failCallback, 5, "Cordova Speech Recognizer Plugin");
	
	function successCallback(results){
		console.log("Results: " + results);
	}
	
	function failCallback(error){
		console.log("Error: " + error);
	}
