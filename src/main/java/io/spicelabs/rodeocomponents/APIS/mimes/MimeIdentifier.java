package io.spicelabs.rodeocomponents.APIS.mimes;

/* Copyright 2026 Stephen Hawley, Spice Labs, Inc. & Contributors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. */

import java.io.InputStream;
import java.util.Optional;

/**
 * The MimeIdentifier interface is used by components to identify file types
 * that may not be identified by Goat Rodeo. A component will typically
 * implement not this interface directly but instead
 * {@link MimeInputStreamIdentifier} or {@link MimeFileInputStreamIdentifier}.
 * <p>
 * Detecting mime types happens in 3 stages. In the first stage, a component
 * reports a header length that it needs to identify the mime type. If the
 * component can't report a header length, it should report
 * {@link MimeConstants#UNKNOWN_BYTES_NEEDED}. In the second stage, each
 * component will be called with an array of bytes representing the header. If
 * the component could possibly recognize the mime type, it should return true,
 * even if it's a false positive. If a component returns false, it will be
 * ignored for the rest of the process. In the third stage, the component will
 * be asked to positively identify the mime type. In this stage the component
 * will be passed an InputStream of some flavor and whatever the mime type has
 * been identified so far. Components should be mindful of overriding any mime
 * type except "application/octet-stream". If a component can't identify the
 * mime type or shouldn't override the current mime type, it should return
 * <code>Optional.empty()</code>.
 * <p>
 * It should be noted that in Goat Rodeo, there is a penalty associated with
 * working with <code>FileInputStream</code> and as such priority is given to
 * components that identify mime types with a plain <code>InputStream</code>.
 * This priority is intentional to prevent a worst case situation where all
 * streams need to be presented as files. All streams will come in with
 * <code>markSupported()</code> returning <code>true</code> and for any call to
 * {@link #identifyMimeType}, the stream will start at position 0. The component
 * does not need to restore the stream position, however, the component is
 * responsible for ensuring that it catches any exceptions that get thrown by
 * stream operations.
 * 
 * @see MimeFileInputStreamIdentifier
 * @see MimeInputStreamIdentifier
 * @see MimeConstants
 */
public interface MimeIdentifier<T extends InputStream> {
	/**
	 * Returns the preferred number of bytes the component needs to decide if it can
	 * possibly handle a stream from its header.
	 * 
	 * @return the number of bytes needed to identify the header or
	 *         {@link MimeConstants#UNKNOWN_BYTES_NEEDED} if it can't determine.
	 */
	int preferredHeaderLength();

	/**
	 * Determines if a component can possibly identify a stream. Copmonents should
	 * be inclined to return <code>true</code> if there is any possibility that it
	 * could identify the stream - whether or not it looks at the header. For
	 * example, there are some file types that can't be identified accurately
	 * without diving deep into the stream, but can be classified as a possibility
	 * without doing a deep dive. This process allows true negatives to be filtered
	 * out with minimum effort and cost. The array, header, will have at least as
	 * many bytes as was returned by {@link #preferredHeaderLength}, but it may be
	 * more. If if stream contained fewer bytes than was requested, the data in the
	 * header array past that point will be invalid. Do not write to the array.
	 * 
	 * @param header the bytes representing the header of the stream
	 * @return true if this component can handle the header, false otherwise.
	 */
	boolean canHandleHeader(byte[] header);

	/**
	 * Identifies the mime type of a stream.
	 * 
	 * @param stream    the stream to identify
	 * @param mimeSoFar the mime type that has been most recently identified or
	 *                  "application/octet-stream" if not yet identified
	 * @return either <code>Optional.empty()</code> if the component can't identify the
	 *         type or an String representing the mime type.
	 */
	Optional<String> identifyMimeType(T stream, String mimeSoFar);
}
