﻿/*
Copyright 2011, AUTHORS.txt (http://ui.operamasks.org/about)
Dual licensed under the MIT or LGPL Version 2 licenses.
*/

/**
 * @fileOverview Contains the first and essential part of the {@link OMEDITOR}
 *		object definition.
 */

// #### Compressed Code
// Must be updated on changes in the script as well as updated in the
// omeditor_source.js and omeditor_basic_source.js files.

// if(!window.OMEDITOR)window.OMEDITOR=(function(){var a={timestamp:'',version:'3.6.1',rev:'7072',_:{},status:'unloaded',basePath:(function(){var d=window.OMEDITOR_BASEPATH||'';if(!d){var e=document.getElementsByTagName('script');for(var f=0;f<e.length;f++){var g=e[f].src.match(/(^|.*[\\\/])omeditor(?:_basic)?(?:_source)?.js(?:\?.*)?$/i);if(g){d=g[1];break;}}}if(d.indexOf(':/')==-1)if(d.indexOf('/')===0)d=location.href.match(/^.*?:\/\/[^\/]*/)[0]+d;else d=location.href.match(/^[^\?]*\/(?:)/)[0]+d;return d;})(),getUrl:function(d){if(d.indexOf(':/')==-1&&d.indexOf('/')!==0)d=this.basePath+d;if(this.timestamp&&d.charAt(d.length-1)!='/')d+=(d.indexOf('?')>=0?'&':'?')+('t=')+this.timestamp;return d;}},b=window.OMEDITOR_GETURL;if(b){var c=a.getUrl;a.getUrl=function(d){return b.call(a,d)||c.call(a,d);};}return a;})();

// #### Raw code
// ATTENTION: read the above "Compressed Code" notes when changing this code.

/* @Packager.RemoveLine
// Avoid having the editor code initialized twice. (#7588)
// Use OMEDITOR.dom to check whether the full omeditor.js code has been loaded
// or just omeditor_basic.js.
// Remove these lines when compressing manually.
if ( window.OMEDITOR && window.OMEDITOR.dom )
	return;
@Packager.RemoveLine */

if ( !window.OMEDITOR )
{
	/**
	 * @name OMEDITOR
	 * @namespace This is the API entry point. The entire OMEditor code runs under this object.
	 * @example
	 */
	window.OMEDITOR = (function()
	{
		var OMEDITOR =
		/** @lends OMEDITOR */
		{

			/**
			 * A constant string unique for each release of OMEditor. Its value
			 * is used, by default, to build the URL for all resources loaded
			 * by the editor code, guaranteeing clean cache results when
			 * upgrading.
			 * @type String
			 * @example
			 * alert( OMEDITOR.timestamp );  // e.g. '87dm'
			 */
			// The production implementation contains a fixed timestamp, unique
			// for each release and generated by the releaser.
			// (Base 36 value of each component of YYMMDDHH - 4 chars total - e.g. 87bm == 08071122)
			timestamp : 'B5GJ5GG',

			/**
			 * Contains the OMEditor version number.
			 * @type String
			 * @example
			 * alert( OMEDITOR.version );  // e.g. 'OMEditor 1.0.0'
			 */
			version : '1.0.0',

			/**
			 * Contains the OMEditor revision number.
			 * The revision number is incremented automatically, following each
			 * modification to the OMEditor source code.
			 * @type String
			 * @example
			 * alert( OMEDITOR.revision );  // e.g. '3975'
			 */
			revision : '7072',

			/**
			 * Private object used to hold core stuff. It should not be used outside of
			 * the API code as properties defined here may change at any time
			 * without notice.
			 * @private
			 */
			_ : {},

			/**
			 * Indicates the API loading status. The following statuses are available:
			 *		<ul>
			 *			<li><b>unloaded</b>: the API is not yet loaded.</li>
			 *			<li><b>basic_loaded</b>: the basic API features are available.</li>
			 *			<li><b>basic_ready</b>: the basic API is ready to load the full core code.</li>
			 *			<li><b>loading</b>: the full API is being loaded.</li>
			 *			<li><b>loaded</b>: the API can be fully used.</li>
			 *		</ul>
			 * @type String
			 * @example
			 * if ( <b>OMEDITOR.status</b> == 'loaded' )
			 * {
			 *     // The API can now be fully used.
			 * }
			 */
			status : 'unloaded',

			/**
			 * Contains the full URL for the OMEditor installation directory.
			 * It is possible to manually provide the base path by setting a
			 * global variable named OMEDITOR_BASEPATH. This global variable
			 * must be set <strong>before</strong> the editor script loading.
			 * @type String
			 * @example
			 * alert( <b>OMEDITOR.basePath</b> );  // "http://www.example.com/omeditor/" (e.g.)
			 */
			basePath : (function()
			{
				// ATTENTION: fixes to this code must be ported to
				// var basePath in "core/loader.js".

				// Find out the editor directory path, based on its <script> tag.
				var path = window.OMEDITOR_BASEPATH || '';

				if ( !path )
				{
					var scripts = document.getElementsByTagName( 'script' );

					for ( var i = 0 ; i < scripts.length ; i++ )
					{
						var match = scripts[i].src.match( /(^|.*[\\\/])omeditor(?:_basic)?(?:_source)?.js(?:\?.*)?$/i );

						if ( match )
						{
							path = match[1];
							break;
						}
					}
				}

				// In IE (only) the script.src string is the raw value entered in the
				// HTML source. Other browsers return the full resolved URL instead.
				if ( path.indexOf(':/') == -1 )
				{
					// Absolute path.
					if ( path.indexOf( '/' ) === 0 )
						path = location.href.match( /^.*?:\/\/[^\/]*/ )[0] + path;
					// Relative path.
					else
						path = location.href.match( /^[^\?]*\/(?:)/ )[0] + path;
				}

				if ( !path )
						throw 'The OMEditor installation path could not be automatically detected. Please set the global variable "OMEDITOR_BASEPATH" before creating editor instances.';

				return path;
			})(),

			/**
			 * Gets the full URL for OMEditor resources. By default, URLs
			 * returned by this function contain a querystring parameter ("t")
			 * set to the {@link OMEDITOR.timestamp} value.<br />
			 * <br />
			 * It is possible to provide a custom implementation of this
			 * function by setting a global variable named OMEDITOR_GETURL.
			 * This global variable must be set <strong>before</strong> the editor script
			 * loading. If the custom implementation returns nothing (==null), the
			 * default implementation is used.
			 * @param {String} resource The resource whose full URL we want to get.
			 *		It may be a full, absolute, or relative URL.
			 * @returns {String} The full URL.
			 * @example
			 * // e.g. http://www.example.com/omeditor/skins/default/editor.css?t=87dm
			 * alert( OMEDITOR.getUrl( 'skins/default/editor.css' ) );
			 * @example
			 * // e.g. http://www.example.com/skins/default/editor.css?t=87dm
			 * alert( OMEDITOR.getUrl( '/skins/default/editor.css' ) );
			 * @example
			 * // e.g. http://www.somesite.com/skins/default/editor.css?t=87dm
			 * alert( OMEDITOR.getUrl( 'http://www.somesite.com/skins/default/editor.css' ) );
			 */
			getUrl : function( resource )
			{
				// If this is not a full or absolute path.
				if ( resource.indexOf(':/') == -1 && resource.indexOf( '/' ) !== 0 )
					resource = this.basePath + resource;

				// Add the timestamp, except for directories.
				if ( this.timestamp && resource.charAt( resource.length - 1 ) != '/' && !(/[&?]t=/).test( resource ) )
					resource += ( resource.indexOf( '?' ) >= 0 ? '&' : '?' ) + 't=' + this.timestamp;

				return resource;
			}
		};

		// Make it possible to override the getUrl function with a custom
		// implementation pointing to a global named OMEDITOR_GETURL.
		var newGetUrl = window.OMEDITOR_GETURL;
		if ( newGetUrl )
		{
			var originalGetUrl = OMEDITOR.getUrl;
			OMEDITOR.getUrl = function ( resource )
			{
				return newGetUrl.call( OMEDITOR, resource ) ||
					originalGetUrl.call( OMEDITOR, resource );
			};
		}

		return OMEDITOR;
	})();
}

/**
 * Function called upon loading a custom configuration file that can
 * modify the editor instance configuration ({@link OMEDITOR.editor#config }).
 * It is usually defined inside the custom configuration files that can
 * include developer defined settings.
 * @name OMEDITOR.editorConfig
 * @function
 * @param {OMEDITOR.config} config A configuration object containing the
 *		settings defined for a {@link OMEDITOR.editor} instance up to this
 *		function call. Note that not all settings may still be available. See
 *		<a href="http://docs.cksource.com/OMEditor_3.x/Developers_Guide/Setting_Configurations#Configuration_Loading_Order">Configuration Loading Order</a>
 *		for details.
 * @example
 * // This is supposed to be placed in the config.js file.
 * OMEDITOR.editorConfig = function( config )
 * {
 *     // Define changes to default configuration here. For example:
 *     config.language = 'fr';
 *     config.uiColor = '#AADC6E';
 * };
 */

// PACKAGER_RENAME( OMEDITOR )
