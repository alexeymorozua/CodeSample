package com.alexeymorozua.codesample.mvp.data.local;

import android.support.annotation.NonNull;
import com.pushtorefresh.storio.sqlite.queries.Query;

/**
 * Created by john on 24.12.2016.
 */

public class RepositoriesDetailTable {

  public RepositoriesDetailTable() {
    throw new IllegalStateException("No instances please");
  }

  @NonNull public static final String TABLE = "Repositories";

  @NonNull public static final String COLUMN_ID = "id";

  @NonNull public static final String COLUMN_NAME = "name";

  @NonNull public static final String COLUMN_FULL_NAME = "full_name";

  @NonNull public static final String COLUMN_DESCRIPTION = "description";

  @NonNull public static final String COLUMN_LANGUAGE = "language";

  @NonNull public static final String COLUMN_STARGAZERS_COUNT = "stargazers_count";

  @NonNull public static final String COLUMN_UPDATE_AT = "updated_at";

  @NonNull public static final String COLUMN_HTML_URL = "html_url";

  @NonNull public static final String COLUMN_AVATAR_URL = "avatar_url";

  @NonNull public static final String COLUMN_LOGIN = "login";

  @NonNull public static final Query QUERY_ALL = Query.builder().table(TABLE).build();

  @NonNull public static String getCreateTableQuery() {
    return "CREATE TABLE "
        + TABLE
        + "("
        + COLUMN_ID
        + " INTEGER NOT NULL PRIMARY KEY, "
        + COLUMN_NAME
        + " TEXT NULL, "
        + COLUMN_FULL_NAME
        + " TEXT NULL, "
        + COLUMN_DESCRIPTION
        + " TEXT NULL, "
        + COLUMN_LANGUAGE
        + " TEXT NULL, "
        + COLUMN_STARGAZERS_COUNT
        + " TEXT NULL, "
        + COLUMN_UPDATE_AT
        + " TEXT NULL, "
        + COLUMN_HTML_URL
        + " TEXT NULL, "
        + COLUMN_AVATAR_URL
        + " TEXT NULL, "
        + COLUMN_LOGIN
        + " TEXT NOT NULL UNIQUE"
        + ");";
  }
}
