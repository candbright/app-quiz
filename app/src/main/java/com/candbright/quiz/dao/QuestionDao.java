package com.candbright.quiz.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import java.util.List;

import com.candbright.quiz.model.data.Question;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "QUESTION".
 */
public class QuestionDao extends AbstractDao<Question, Long> {

    public static final String TABLENAME = "QUESTION";

    /**
     * Properties of entity Question.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Difficulty = new Property(1, int.class, "difficulty", false, "DIFFICULTY");
        public final static Property Subject = new Property(2, int.class, "subject", false, "SUBJECT");
        public final static Property Description = new Property(3, String.class, "description", false, "DESCRIPTION");
        public final static Property QuestionType = new Property(4, int.class, "questionType", false, "QUESTION_TYPE");
        public final static Property Answer = new Property(5, String.class, "answer", false, "ANSWER");
        public final static Property UrlPath = new Property(6, String.class, "urlPath", false, "URL_PATH");
    }

    private final StringConverter urlPathConverter = new StringConverter();

    public QuestionDao(DaoConfig config) {
        super(config);
    }

    public QuestionDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"QUESTION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"DIFFICULTY\" INTEGER NOT NULL ," + // 1: difficulty
                "\"SUBJECT\" INTEGER NOT NULL ," + // 2: subject
                "\"DESCRIPTION\" TEXT," + // 3: description
                "\"QUESTION_TYPE\" INTEGER NOT NULL ," + // 4: questionType
                "\"ANSWER\" TEXT," + // 5: answer
                "\"URL_PATH\" TEXT);"); // 6: urlPath
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"QUESTION\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Question entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getDifficulty());
        stmt.bindLong(3, entity.getSubject());

        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(4, description);
        }
        stmt.bindLong(5, entity.getQuestionType());

        String answer = entity.getAnswer();
        if (answer != null) {
            stmt.bindString(6, answer);
        }

        List urlPath = entity.getUrlPath();
        if (urlPath != null) {
            stmt.bindString(7, urlPathConverter.convertToDatabaseValue(urlPath));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Question entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getDifficulty());
        stmt.bindLong(3, entity.getSubject());

        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(4, description);
        }
        stmt.bindLong(5, entity.getQuestionType());

        String answer = entity.getAnswer();
        if (answer != null) {
            stmt.bindString(6, answer);
        }

        List urlPath = entity.getUrlPath();
        if (urlPath != null) {
            stmt.bindString(7, urlPathConverter.convertToDatabaseValue(urlPath));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public Question readEntity(Cursor cursor, int offset) {
        Question entity = new Question( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getInt(offset + 1), // difficulty
                cursor.getInt(offset + 2), // subject
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // description
                cursor.getInt(offset + 4), // questionType
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // answer
                cursor.isNull(offset + 6) ? null : urlPathConverter.convertToEntityProperty(cursor.getString(offset + 6)) // urlPath
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, Question entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDifficulty(cursor.getInt(offset + 1));
        entity.setSubject(cursor.getInt(offset + 2));
        entity.setDescription(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setQuestionType(cursor.getInt(offset + 4));
        entity.setAnswer(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUrlPath(cursor.isNull(offset + 6) ? null : urlPathConverter.convertToEntityProperty(cursor.getString(offset + 6)));
    }

    @Override
    protected final Long updateKeyAfterInsert(Question entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(Question entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Question entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

}
