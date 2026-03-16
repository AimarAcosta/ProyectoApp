package com.example.controlalmacenapp.model.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.controlalmacenapp.model.entities.UsuarioEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UsuarioDao_Impl implements UsuarioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UsuarioEntity> __insertionAdapterOfUsuarioEntity;

  private final EntityDeletionOrUpdateAdapter<UsuarioEntity> __deletionAdapterOfUsuarioEntity;

  private final EntityDeletionOrUpdateAdapter<UsuarioEntity> __updateAdapterOfUsuarioEntity;

  public UsuarioDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsuarioEntity = new EntityInsertionAdapter<UsuarioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `usuarios` (`id`,`nombre`,`imagenUrl`,`es_administrador`,`password`,`email`,`habilitado`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UsuarioEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNombre());
        if (entity.getImagenUrl() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getImagenUrl());
        }
        final int _tmp = entity.getEsAdministrador() ? 1 : 0;
        statement.bindLong(4, _tmp);
        if (entity.getPassword() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPassword());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEmail());
        }
        final int _tmp_1 = entity.getHabilitado() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
      }
    };
    this.__deletionAdapterOfUsuarioEntity = new EntityDeletionOrUpdateAdapter<UsuarioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `usuarios` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UsuarioEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfUsuarioEntity = new EntityDeletionOrUpdateAdapter<UsuarioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `usuarios` SET `id` = ?,`nombre` = ?,`imagenUrl` = ?,`es_administrador` = ?,`password` = ?,`email` = ?,`habilitado` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UsuarioEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNombre());
        if (entity.getImagenUrl() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getImagenUrl());
        }
        final int _tmp = entity.getEsAdministrador() ? 1 : 0;
        statement.bindLong(4, _tmp);
        if (entity.getPassword() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPassword());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEmail());
        }
        final int _tmp_1 = entity.getHabilitado() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insertUsuario(final UsuarioEntity usuario,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUsuarioEntity.insertAndReturnId(usuario);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertUsuarioLista(final List<UsuarioEntity> usuarios,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfUsuarioEntity.insertAndReturnIdsList(usuarios);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUsuario(final UsuarioEntity usuario,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __deletionAdapterOfUsuarioEntity.handle(usuario);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateUsuario(final UsuarioEntity usuario,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __updateAdapterOfUsuarioEntity.handle(usuario);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getUsuariosHabilitados(
      final Continuation<? super List<UsuarioEntity>> $completion) {
    final String _sql = "SELECT * FROM usuarios WHERE habilitado = 1 ORDER BY nombre ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsuarioEntity>>() {
      @Override
      @NonNull
      public List<UsuarioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfImagenUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenUrl");
          final int _cursorIndexOfEsAdministrador = CursorUtil.getColumnIndexOrThrow(_cursor, "es_administrador");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfHabilitado = CursorUtil.getColumnIndexOrThrow(_cursor, "habilitado");
          final List<UsuarioEntity> _result = new ArrayList<UsuarioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsuarioEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpImagenUrl;
            if (_cursor.isNull(_cursorIndexOfImagenUrl)) {
              _tmpImagenUrl = null;
            } else {
              _tmpImagenUrl = _cursor.getString(_cursorIndexOfImagenUrl);
            }
            final boolean _tmpEsAdministrador;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEsAdministrador);
            _tmpEsAdministrador = _tmp != 0;
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final boolean _tmpHabilitado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfHabilitado);
            _tmpHabilitado = _tmp_1 != 0;
            _item = new UsuarioEntity(_tmpId,_tmpNombre,_tmpImagenUrl,_tmpEsAdministrador,_tmpPassword,_tmpEmail,_tmpHabilitado);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object loginAdmin(final String nombre, final String password,
      final Continuation<? super UsuarioEntity> $completion) {
    final String _sql = "SELECT * FROM usuarios WHERE nombre = ? AND password = ? AND es_administrador = 1 AND habilitado = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, nombre);
    _argIndex = 2;
    _statement.bindString(_argIndex, password);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UsuarioEntity>() {
      @Override
      @Nullable
      public UsuarioEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfImagenUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenUrl");
          final int _cursorIndexOfEsAdministrador = CursorUtil.getColumnIndexOrThrow(_cursor, "es_administrador");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfHabilitado = CursorUtil.getColumnIndexOrThrow(_cursor, "habilitado");
          final UsuarioEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpImagenUrl;
            if (_cursor.isNull(_cursorIndexOfImagenUrl)) {
              _tmpImagenUrl = null;
            } else {
              _tmpImagenUrl = _cursor.getString(_cursorIndexOfImagenUrl);
            }
            final boolean _tmpEsAdministrador;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEsAdministrador);
            _tmpEsAdministrador = _tmp != 0;
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final boolean _tmpHabilitado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfHabilitado);
            _tmpHabilitado = _tmp_1 != 0;
            _result = new UsuarioEntity(_tmpId,_tmpNombre,_tmpImagenUrl,_tmpEsAdministrador,_tmpPassword,_tmpEmail,_tmpHabilitado);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object obtenerUsuarioPorNombre(final String nombreUsuario,
      final Continuation<? super UsuarioEntity> $completion) {
    final String _sql = "SELECT * FROM usuarios WHERE nombre = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, nombreUsuario);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UsuarioEntity>() {
      @Override
      @Nullable
      public UsuarioEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfImagenUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenUrl");
          final int _cursorIndexOfEsAdministrador = CursorUtil.getColumnIndexOrThrow(_cursor, "es_administrador");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfHabilitado = CursorUtil.getColumnIndexOrThrow(_cursor, "habilitado");
          final UsuarioEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpImagenUrl;
            if (_cursor.isNull(_cursorIndexOfImagenUrl)) {
              _tmpImagenUrl = null;
            } else {
              _tmpImagenUrl = _cursor.getString(_cursorIndexOfImagenUrl);
            }
            final boolean _tmpEsAdministrador;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEsAdministrador);
            _tmpEsAdministrador = _tmp != 0;
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final boolean _tmpHabilitado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfHabilitado);
            _tmpHabilitado = _tmp_1 != 0;
            _result = new UsuarioEntity(_tmpId,_tmpNombre,_tmpImagenUrl,_tmpEsAdministrador,_tmpPassword,_tmpEmail,_tmpHabilitado);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
