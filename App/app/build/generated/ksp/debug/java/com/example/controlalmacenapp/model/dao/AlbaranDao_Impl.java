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
import com.example.controlalmacenapp.model.entities.AlbaranEntity;
import java.lang.Class;
import java.lang.Exception;
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
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AlbaranDao_Impl implements AlbaranDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AlbaranEntity> __insertionAdapterOfAlbaranEntity;

  private final EntityDeletionOrUpdateAdapter<AlbaranEntity> __deletionAdapterOfAlbaranEntity;

  private final EntityDeletionOrUpdateAdapter<AlbaranEntity> __updateAdapterOfAlbaranEntity;

  public AlbaranDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAlbaranEntity = new EntityInsertionAdapter<AlbaranEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `albaranes` (`id`,`proveedor_cif`,`fecha_emision`,`importe`,`pagado`,`fecha_pago`,`foto_uri`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AlbaranEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getProveedorCif());
        statement.bindLong(3, entity.getFechaEmision());
        statement.bindDouble(4, entity.getImporte());
        final int _tmp = entity.getPagado() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getFechaPago() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getFechaPago());
        }
        if (entity.getFotoUri() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getFotoUri());
        }
      }
    };
    this.__deletionAdapterOfAlbaranEntity = new EntityDeletionOrUpdateAdapter<AlbaranEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `albaranes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AlbaranEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAlbaranEntity = new EntityDeletionOrUpdateAdapter<AlbaranEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `albaranes` SET `id` = ?,`proveedor_cif` = ?,`fecha_emision` = ?,`importe` = ?,`pagado` = ?,`fecha_pago` = ?,`foto_uri` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AlbaranEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getProveedorCif());
        statement.bindLong(3, entity.getFechaEmision());
        statement.bindDouble(4, entity.getImporte());
        final int _tmp = entity.getPagado() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getFechaPago() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getFechaPago());
        }
        if (entity.getFotoUri() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getFotoUri());
        }
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insertAlbaran(final AlbaranEntity albaran,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAlbaranEntity.insertAndReturnId(albaran);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAlbaran(final AlbaranEntity albaran,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAlbaranEntity.handle(albaran);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAlbaran(final AlbaranEntity albaran,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAlbaranEntity.handle(albaran);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object obtenerAlbaranesDeProveedor(final String cif,
      final Continuation<? super List<AlbaranEntity>> $completion) {
    final String _sql = "SELECT * FROM albaranes WHERE proveedor_cif = ? ORDER BY fecha_emision DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, cif);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AlbaranEntity>>() {
      @Override
      @NonNull
      public List<AlbaranEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProveedorCif = CursorUtil.getColumnIndexOrThrow(_cursor, "proveedor_cif");
          final int _cursorIndexOfFechaEmision = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha_emision");
          final int _cursorIndexOfImporte = CursorUtil.getColumnIndexOrThrow(_cursor, "importe");
          final int _cursorIndexOfPagado = CursorUtil.getColumnIndexOrThrow(_cursor, "pagado");
          final int _cursorIndexOfFechaPago = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha_pago");
          final int _cursorIndexOfFotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "foto_uri");
          final List<AlbaranEntity> _result = new ArrayList<AlbaranEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AlbaranEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpProveedorCif;
            _tmpProveedorCif = _cursor.getString(_cursorIndexOfProveedorCif);
            final long _tmpFechaEmision;
            _tmpFechaEmision = _cursor.getLong(_cursorIndexOfFechaEmision);
            final double _tmpImporte;
            _tmpImporte = _cursor.getDouble(_cursorIndexOfImporte);
            final boolean _tmpPagado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPagado);
            _tmpPagado = _tmp != 0;
            final Long _tmpFechaPago;
            if (_cursor.isNull(_cursorIndexOfFechaPago)) {
              _tmpFechaPago = null;
            } else {
              _tmpFechaPago = _cursor.getLong(_cursorIndexOfFechaPago);
            }
            final String _tmpFotoUri;
            if (_cursor.isNull(_cursorIndexOfFotoUri)) {
              _tmpFotoUri = null;
            } else {
              _tmpFotoUri = _cursor.getString(_cursorIndexOfFotoUri);
            }
            _item = new AlbaranEntity(_tmpId,_tmpProveedorCif,_tmpFechaEmision,_tmpImporte,_tmpPagado,_tmpFechaPago,_tmpFotoUri);
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
  public Object obtenerAlbaranesPendientes(
      final Continuation<? super List<AlbaranEntity>> $completion) {
    final String _sql = "SELECT * FROM albaranes WHERE pagado = 0 ORDER BY fecha_emision ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AlbaranEntity>>() {
      @Override
      @NonNull
      public List<AlbaranEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProveedorCif = CursorUtil.getColumnIndexOrThrow(_cursor, "proveedor_cif");
          final int _cursorIndexOfFechaEmision = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha_emision");
          final int _cursorIndexOfImporte = CursorUtil.getColumnIndexOrThrow(_cursor, "importe");
          final int _cursorIndexOfPagado = CursorUtil.getColumnIndexOrThrow(_cursor, "pagado");
          final int _cursorIndexOfFechaPago = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha_pago");
          final int _cursorIndexOfFotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "foto_uri");
          final List<AlbaranEntity> _result = new ArrayList<AlbaranEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AlbaranEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpProveedorCif;
            _tmpProveedorCif = _cursor.getString(_cursorIndexOfProveedorCif);
            final long _tmpFechaEmision;
            _tmpFechaEmision = _cursor.getLong(_cursorIndexOfFechaEmision);
            final double _tmpImporte;
            _tmpImporte = _cursor.getDouble(_cursorIndexOfImporte);
            final boolean _tmpPagado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPagado);
            _tmpPagado = _tmp != 0;
            final Long _tmpFechaPago;
            if (_cursor.isNull(_cursorIndexOfFechaPago)) {
              _tmpFechaPago = null;
            } else {
              _tmpFechaPago = _cursor.getLong(_cursorIndexOfFechaPago);
            }
            final String _tmpFotoUri;
            if (_cursor.isNull(_cursorIndexOfFotoUri)) {
              _tmpFotoUri = null;
            } else {
              _tmpFotoUri = _cursor.getString(_cursorIndexOfFotoUri);
            }
            _item = new AlbaranEntity(_tmpId,_tmpProveedorCif,_tmpFechaEmision,_tmpImporte,_tmpPagado,_tmpFechaPago,_tmpFotoUri);
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
  public Object obtenerAlbaranPorId(final int id,
      final Continuation<? super AlbaranEntity> $completion) {
    final String _sql = "SELECT * FROM albaranes WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AlbaranEntity>() {
      @Override
      @Nullable
      public AlbaranEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProveedorCif = CursorUtil.getColumnIndexOrThrow(_cursor, "proveedor_cif");
          final int _cursorIndexOfFechaEmision = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha_emision");
          final int _cursorIndexOfImporte = CursorUtil.getColumnIndexOrThrow(_cursor, "importe");
          final int _cursorIndexOfPagado = CursorUtil.getColumnIndexOrThrow(_cursor, "pagado");
          final int _cursorIndexOfFechaPago = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha_pago");
          final int _cursorIndexOfFotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "foto_uri");
          final AlbaranEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpProveedorCif;
            _tmpProveedorCif = _cursor.getString(_cursorIndexOfProveedorCif);
            final long _tmpFechaEmision;
            _tmpFechaEmision = _cursor.getLong(_cursorIndexOfFechaEmision);
            final double _tmpImporte;
            _tmpImporte = _cursor.getDouble(_cursorIndexOfImporte);
            final boolean _tmpPagado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPagado);
            _tmpPagado = _tmp != 0;
            final Long _tmpFechaPago;
            if (_cursor.isNull(_cursorIndexOfFechaPago)) {
              _tmpFechaPago = null;
            } else {
              _tmpFechaPago = _cursor.getLong(_cursorIndexOfFechaPago);
            }
            final String _tmpFotoUri;
            if (_cursor.isNull(_cursorIndexOfFotoUri)) {
              _tmpFotoUri = null;
            } else {
              _tmpFotoUri = _cursor.getString(_cursorIndexOfFotoUri);
            }
            _result = new AlbaranEntity(_tmpId,_tmpProveedorCif,_tmpFechaEmision,_tmpImporte,_tmpPagado,_tmpFechaPago,_tmpFotoUri);
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
  public Object obtenerTodosLosAlbaranes(
      final Continuation<? super List<AlbaranEntity>> $completion) {
    final String _sql = "SELECT * FROM albaranes ORDER BY fecha_emision DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AlbaranEntity>>() {
      @Override
      @NonNull
      public List<AlbaranEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProveedorCif = CursorUtil.getColumnIndexOrThrow(_cursor, "proveedor_cif");
          final int _cursorIndexOfFechaEmision = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha_emision");
          final int _cursorIndexOfImporte = CursorUtil.getColumnIndexOrThrow(_cursor, "importe");
          final int _cursorIndexOfPagado = CursorUtil.getColumnIndexOrThrow(_cursor, "pagado");
          final int _cursorIndexOfFechaPago = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha_pago");
          final int _cursorIndexOfFotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "foto_uri");
          final List<AlbaranEntity> _result = new ArrayList<AlbaranEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AlbaranEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpProveedorCif;
            _tmpProveedorCif = _cursor.getString(_cursorIndexOfProveedorCif);
            final long _tmpFechaEmision;
            _tmpFechaEmision = _cursor.getLong(_cursorIndexOfFechaEmision);
            final double _tmpImporte;
            _tmpImporte = _cursor.getDouble(_cursorIndexOfImporte);
            final boolean _tmpPagado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPagado);
            _tmpPagado = _tmp != 0;
            final Long _tmpFechaPago;
            if (_cursor.isNull(_cursorIndexOfFechaPago)) {
              _tmpFechaPago = null;
            } else {
              _tmpFechaPago = _cursor.getLong(_cursorIndexOfFechaPago);
            }
            final String _tmpFotoUri;
            if (_cursor.isNull(_cursorIndexOfFotoUri)) {
              _tmpFotoUri = null;
            } else {
              _tmpFotoUri = _cursor.getString(_cursorIndexOfFotoUri);
            }
            _item = new AlbaranEntity(_tmpId,_tmpProveedorCif,_tmpFechaEmision,_tmpImporte,_tmpPagado,_tmpFechaPago,_tmpFotoUri);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
