/*
 * This file is generated by jOOQ.
 */
package edu.uci.ics.texera.web.model.jooq.generated.tables;


import edu.uci.ics.texera.web.model.jooq.generated.Indexes;
import edu.uci.ics.texera.web.model.jooq.generated.Keys;
import edu.uci.ics.texera.web.model.jooq.generated.TexeraDb;
import edu.uci.ics.texera.web.model.jooq.generated.tables.records.FileRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class File extends TableImpl<FileRecord> {

    private static final long serialVersionUID = 1036017326;

    /**
     * The reference instance of <code>texera_db.file</code>
     */
    public static final File FILE = new File();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FileRecord> getRecordType() {
        return FileRecord.class;
    }

    /**
     * The column <code>texera_db.file.owner_uid</code>.
     */
    public final TableField<FileRecord, UInteger> OWNER_UID = createField(DSL.name("owner_uid"), org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>texera_db.file.fid</code>.
     */
    public final TableField<FileRecord, UInteger> FID = createField(DSL.name("fid"), org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>texera_db.file.size</code>.
     */
    public final TableField<FileRecord, UInteger> SIZE = createField(DSL.name("size"), org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>texera_db.file.name</code>.
     */
    public final TableField<FileRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>texera_db.file.path</code>.
     */
    public final TableField<FileRecord, String> PATH = createField(DSL.name("path"), org.jooq.impl.SQLDataType.VARCHAR(512).nullable(false), this, "");

    /**
     * The column <code>texera_db.file.description</code>.
     */
    public final TableField<FileRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.VARCHAR(512).nullable(false), this, "");

    /**
     * The column <code>texera_db.file.upload_time</code>.
     */
    public final TableField<FileRecord, Timestamp> UPLOAD_TIME = createField(DSL.name("upload_time"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>texera_db.file</code> table reference
     */
    public File() {
        this(DSL.name("file"), null);
    }

    /**
     * Create an aliased <code>texera_db.file</code> table reference
     */
    public File(String alias) {
        this(DSL.name(alias), FILE);
    }

    /**
     * Create an aliased <code>texera_db.file</code> table reference
     */
    public File(Name alias) {
        this(alias, FILE);
    }

    private File(Name alias, Table<FileRecord> aliased) {
        this(alias, aliased, null);
    }

    private File(Name alias, Table<FileRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> File(Table<O> child, ForeignKey<O, FileRecord> key) {
        super(child, key, FILE);
    }

    @Override
    public Schema getSchema() {
        return TexeraDb.TEXERA_DB;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FILE_OWNER_UID, Indexes.FILE_PRIMARY);
    }

    @Override
    public Identity<FileRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_FILE;
    }

    @Override
    public UniqueKey<FileRecord> getPrimaryKey() {
        return Keys.KEY_FILE_PRIMARY;
    }

    @Override
    public List<UniqueKey<FileRecord>> getKeys() {
        return Arrays.<UniqueKey<FileRecord>>asList(Keys.KEY_FILE_OWNER_UID, Keys.KEY_FILE_PRIMARY);
    }

    @Override
    public List<ForeignKey<FileRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<FileRecord, ?>>asList(Keys.FILE_IBFK_1);
    }

    public User user() {
        return new User(this, Keys.FILE_IBFK_1);
    }

    @Override
    public File as(String alias) {
        return new File(DSL.name(alias), this);
    }

    @Override
    public File as(Name alias) {
        return new File(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public File rename(String name) {
        return new File(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public File rename(Name name) {
        return new File(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<UInteger, UInteger, UInteger, String, String, String, Timestamp> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
