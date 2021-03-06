/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0
 * United States License. To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-sa/3.0/us/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 *
 * Copyright (c) 2012, https://stackoverflow.com/users/1270457/amas
 * Copyright (c) 2012, https://stackoverflow.com/questions/11945728/how-to-use-termvector-lucene-4-0
 * Portions Copyright (c) 2018, Chris Fraire <cfraire@me.com>.
 */

package org.opensolaris.opengrok.analysis;

import java.io.Reader;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;

/**
 * Represents an OpenGrok-customized tokenized, text field with stored term
 * vectors to centralize settings across all the analyzers.
 */
public class OGKTextVecField extends Field {

    /** Indexed, tokenized, not stored. */
    public static final FieldType TYPE_NOT_STORED = new FieldType();

    /** Indexed, tokenized, stored. */
    public static final FieldType TYPE_STORED = new FieldType();

    static {
        TYPE_NOT_STORED.setIndexOptions(
            IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        TYPE_NOT_STORED.setTokenized(true);
        TYPE_NOT_STORED.setStoreTermVectors(true);
        TYPE_NOT_STORED.freeze();

        TYPE_STORED.setIndexOptions(
            IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        TYPE_STORED.setTokenized(true);
        TYPE_STORED.setStoreTermVectors(true);
        TYPE_STORED.setStored(true);
        TYPE_STORED.freeze();
    }

    /**
     * Creates a new un-stored instance with {@link Reader} value.
     */
    public OGKTextVecField(String name, Reader reader) {
        super(name, reader, TYPE_NOT_STORED);
    }

    /**
     * Creates a new instance with {@link Reader} value.
     */
    public OGKTextVecField(String name, Reader reader, Store store) {
        super(name, reader, store == Store.YES ? TYPE_STORED : TYPE_NOT_STORED);
    }

    /**
     * Creates a new instance with {@code String} value.
     */
    public OGKTextVecField(String name, String value, Store store) {
        super(name, value, store == Store.YES ? TYPE_STORED : TYPE_NOT_STORED);
    }

    /**
     * Creates a new un-stored instance with {@link TokenStream} value.
     */
    public OGKTextVecField(String name, TokenStream stream) {
        super(name, stream, TYPE_NOT_STORED);
    }
}
