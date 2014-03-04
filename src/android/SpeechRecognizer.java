/*
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
*/

package com.manueldeveloper;

import java.util.ArrayList;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;

public class SpeechRecognizer extends CordovaPlugin {

	//private static final String SpeechRecognizer_LOG= "SpeechRecognizer";
	private CallbackContext speechRecognizerCallbackContext;
	
	private static final int REQUEST_CODE= 1;
	
	private Intent recognizerIntent;
	private ArrayList<String> results;
	private String promptMessage;
	private int maxResults;
	
	public SpeechRecognizer(){
		speechRecognizerCallbackContext= null;
		promptMessage= null;
		maxResults= 1;
	}
	
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException{		
		// Check action
		if( action.equals("recognize") ){
			// Get the reference to the callbacks and parameters
			this.speechRecognizerCallbackContext= callbackContext;
			if( args.length() > 0 ){
				if( args.getInt(0) > 1 )
					maxResults= args.getInt(0);
				else{
					callbackContext.error("maxResults argument must be more than 1");
					return true;
				}
			}			
			if( args.length() > 1 )
				promptMessage= args.getString(1);
			
			// Start the recognition process
			recognizerIntent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, maxResults);
			if( promptMessage != null )
				recognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, promptMessage);
			cordova.startActivityForResult(this, recognizerIntent, REQUEST_CODE);
			
			return true;
		}
		
		return false;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		// Check the request code
		if( requestCode == REQUEST_CODE ){
			// Check the result code
			if( resultCode == Activity.RESULT_OK ){
				// Get the results
				this.results= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				sendResults();
			}
			else{
				if( this.speechRecognizerCallbackContext != null ){
					switch( resultCode ){
						case RecognizerIntent.RESULT_NETWORK_ERROR: this.speechRecognizerCallbackContext.error("NETWORK_ERROR");
																	break;
						case RecognizerIntent.RESULT_CLIENT_ERROR: 	this.speechRecognizerCallbackContext.error("CLIENT_ERROR");
																	break;
						case RecognizerIntent.RESULT_SERVER_ERROR: 	this.speechRecognizerCallbackContext.error("SERVER_ERROR");
																	break;
						case RecognizerIntent.RESULT_AUDIO_ERROR: 	this.speechRecognizerCallbackContext.error("AUDIO_ERROR");
																	break;					
						case RecognizerIntent.RESULT_NO_MATCH: 		this.speechRecognizerCallbackContext.error("NO_MATCH");
																	break;	
					}
				}
			}
		}
	}
	
	private void sendResults(){
		if( this.speechRecognizerCallbackContext != null ){
			PluginResult result= new PluginResult(PluginResult.Status.OK, new JSONArray(this.results));
			result.setKeepCallback(false);
			this.speechRecognizerCallbackContext.sendPluginResult(result);
		}
	}
}
