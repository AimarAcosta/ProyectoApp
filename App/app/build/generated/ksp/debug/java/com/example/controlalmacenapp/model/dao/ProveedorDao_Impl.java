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
import com.example.controlalmacenapp.model.entities.ProveedorEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProveedorDao_Impl implements ProveedorDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProveedorEntity> __insertionAdapterOfProveedorEntity;

  private final EntityDeletionOrUpdateAdapter<ProveedorEntity> __deletionAdapterOfProveedorEntity;

  private final EntityDeletionOrUpdateAdapter<ProveedorEntity> __updateAdapterOfProveedorEntity;

  public ProveedorDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProveedorEntity = new EntityInsertionAdapter<ProveedorEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `proveedores` (`cif`,`nombre`,`telefono`,`email`,`habilitado`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProveedorEntity entity) {
        statement.bindString(1, entity.getCif());
        statement.bindString(2, entity.getNombre());
        statement.bindString(3, entity.getTelefono());
        statement.bindString(4, entity.getEmail());
        final int _tmp = entity.getHabilitado() ? 1 : 0;
        statement.bindLong(5, _tmp);
      }
    };
    this.__deletionAdapterOfProveedorEntity = new EntityDeletionOrUpdateAdapter<ProveedorEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `proveedores` WHERE `cif` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProveedorEntity entity) {
        statement.bindString(1, entity.getCif());
      }
    };
    this.__updateAdapterOfProveedorEntity = new EntityDeletionOrUpdateAdapter<ProveedorEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `proveedores` SET `cif` = ?,`nombre` = ?,`telefono` = ?,`email` = ?,`habilitado` = ? WHERE `cif` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProveedorEntity entity) {
        statement.bindString(1, entity.getCif());
        statement.bindString(2, entity.getNombre());
        statement.bindString(3, entity.getTelefono());
        statement.bindString(4, entity.getEmail());
        final int _tmp = entity.getHabilitado() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindString(6, entity.getCif());
      }
    };
  }

  @Override
  public Object insertProveedor(final ProveedorEntity proveedor,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProveedorEntity.insert(proveedor);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteProveedor(final ProveedorEntity proveedor,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfProveedorEntity.handle(proveedor);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateProveedor(final ProveedorEntity proveedor,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfProveedorEntity.handle(proveedor);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object obtenerProveedoresActivos(
      final Continuation<? super List<ProveedorEntity>> $completion) {
    final String _sql = "SELECT * FROM proveedores WHERE habilitado = 1 ORDER BY nombre ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ProveedorEntity>>() {
      @Override
      @NonNull
      public List<ProveedorEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCif = CursorUtil.getColumnIndexOrThrow(_cursor, "cif");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfTelefono = CursorUtil.getColumnIndexOrThrow(_cursor, "telefono");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfHabilitado = CursorUtil.getColumnIndexOrThrow(_cursor, "habilitado");
          final List<ProveedorEntity> _result = new ArrayList<ProveedorEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProveedorEntity _item;
            final String _tmpCif;
            _tmpCif = _cursor.getString(_cursorIndexOfCif);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpTelefono;
            _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final boolean _tmpHabilitado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHabilitado);
            _tmpHabilitado = _tmp != 0;
            _item = new ProveedorEntity(_tmpCif,_tmpNombre,_tmpTelefono,_tmpEmail,_tmpHabilitado);
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
  public Object obtenerProveedorPorCif(final String cif,
      final Continuation<? super ProveedorEntity> $completion) {
    final String _sql = "SELECT * FROM proveedores WHERE cif = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, cif);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProveedorEntity>() {
      @Override
      @Nullable
      public ProveedorEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCif = CursorUtil.getColumnIndexOrThrow(_cursor, "cif");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfTelefono = CursorUtil.getColumnIndexOrThrow(_cursor, "telefono");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfHabilitado = CursorUtil.getColumnIndexOrThrow(_cursor, "habilitado");
          final ProveedorEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpCif;
            _tmpCif = _cursor.getString(_cursorIndexOfCif);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpTelefono;
            _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final boolean _tmpHabilitado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHabilitado);
            _tmpHabilitado = _tmp != 0;
            _result = new ProveedorEntity(_tmpCif,_tmpNombre,_tmpTelefono,_tmpEmail,_tmpHabilitado);
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
