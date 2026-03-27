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
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.controlalmacenapp.model.entities.ProductoEntity;
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
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProductoDao_Impl implements ProductoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProductoEntity> __insertionAdapterOfProductoEntity;

  private final EntityDeletionOrUpdateAdapter<ProductoEntity> __deletionAdapterOfProductoEntity;

  private final EntityDeletionOrUpdateAdapter<ProductoEntity> __updateAdapterOfProductoEntity;

  private final SharedSQLiteStatement __preparedStmtOfIncrementarStock;

  private final SharedSQLiteStatement __preparedStmtOfDecrementarStock;

  public ProductoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProductoEntity = new EntityInsertionAdapter<ProductoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `productos` (`id`,`nombre`,`imagenUrl`,`cantidad`,`cantidad_minima`,`habilitado`,`ultima_interaccion`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNombre());
        if (entity.getImagenUrl() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getImagenUrl());
        }
        statement.bindLong(4, entity.getCantidad());
        statement.bindLong(5, entity.getCantidadMinima());
        final int _tmp = entity.getHabilitado() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getUltimaInteraccion());
      }
    };
    this.__deletionAdapterOfProductoEntity = new EntityDeletionOrUpdateAdapter<ProductoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `productos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductoEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfProductoEntity = new EntityDeletionOrUpdateAdapter<ProductoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `productos` SET `id` = ?,`nombre` = ?,`imagenUrl` = ?,`cantidad` = ?,`cantidad_minima` = ?,`habilitado` = ?,`ultima_interaccion` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNombre());
        if (entity.getImagenUrl() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getImagenUrl());
        }
        statement.bindLong(4, entity.getCantidad());
        statement.bindLong(5, entity.getCantidadMinima());
        final int _tmp = entity.getHabilitado() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getUltimaInteraccion());
        statement.bindLong(8, entity.getId());
      }
    };
    this.__preparedStmtOfIncrementarStock = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE productos SET cantidad = cantidad + 1, ultima_interaccion = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDecrementarStock = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE productos SET cantidad = cantidad - 1, ultima_interaccion = ? WHERE id = ? AND cantidad > 0";
        return _query;
      }
    };
  }

  @Override
  public Object insertProducto(final ProductoEntity producto,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfProductoEntity.insertAndReturnId(producto);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteProducto(final ProductoEntity producto,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __deletionAdapterOfProductoEntity.handle(producto);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateProducto(final ProductoEntity producto,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __updateAdapterOfProductoEntity.handle(producto);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object incrementarStock(final int id, final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementarStock.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfIncrementarStock.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object decrementarStock(final int id, final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDecrementarStock.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDecrementarStock.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object obtenerTodosLosProductos(
      final Continuation<? super List<ProductoEntity>> $completion) {
    final String _sql = "SELECT * FROM productos ORDER BY nombre ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ProductoEntity>>() {
      @Override
      @NonNull
      public List<ProductoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfImagenUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenUrl");
          final int _cursorIndexOfCantidad = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad");
          final int _cursorIndexOfCantidadMinima = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad_minima");
          final int _cursorIndexOfHabilitado = CursorUtil.getColumnIndexOrThrow(_cursor, "habilitado");
          final int _cursorIndexOfUltimaInteraccion = CursorUtil.getColumnIndexOrThrow(_cursor, "ultima_interaccion");
          final List<ProductoEntity> _result = new ArrayList<ProductoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductoEntity _item;
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
            final int _tmpCantidad;
            _tmpCantidad = _cursor.getInt(_cursorIndexOfCantidad);
            final int _tmpCantidadMinima;
            _tmpCantidadMinima = _cursor.getInt(_cursorIndexOfCantidadMinima);
            final boolean _tmpHabilitado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHabilitado);
            _tmpHabilitado = _tmp != 0;
            final long _tmpUltimaInteraccion;
            _tmpUltimaInteraccion = _cursor.getLong(_cursorIndexOfUltimaInteraccion);
            _item = new ProductoEntity(_tmpId,_tmpNombre,_tmpImagenUrl,_tmpCantidad,_tmpCantidadMinima,_tmpHabilitado,_tmpUltimaInteraccion);
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
  public Object obtenerProductosHabituales(
      final Continuation<? super List<ProductoEntity>> $completion) {
    final String _sql = "SELECT * FROM productos WHERE habilitado = 1 ORDER BY ultima_interaccion DESC LIMIT 10";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ProductoEntity>>() {
      @Override
      @NonNull
      public List<ProductoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfImagenUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenUrl");
          final int _cursorIndexOfCantidad = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad");
          final int _cursorIndexOfCantidadMinima = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad_minima");
          final int _cursorIndexOfHabilitado = CursorUtil.getColumnIndexOrThrow(_cursor, "habilitado");
          final int _cursorIndexOfUltimaInteraccion = CursorUtil.getColumnIndexOrThrow(_cursor, "ultima_interaccion");
          final List<ProductoEntity> _result = new ArrayList<ProductoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductoEntity _item;
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
            final int _tmpCantidad;
            _tmpCantidad = _cursor.getInt(_cursorIndexOfCantidad);
            final int _tmpCantidadMinima;
            _tmpCantidadMinima = _cursor.getInt(_cursorIndexOfCantidadMinima);
            final boolean _tmpHabilitado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHabilitado);
            _tmpHabilitado = _tmp != 0;
            final long _tmpUltimaInteraccion;
            _tmpUltimaInteraccion = _cursor.getLong(_cursorIndexOfUltimaInteraccion);
            _item = new ProductoEntity(_tmpId,_tmpNombre,_tmpImagenUrl,_tmpCantidad,_tmpCantidadMinima,_tmpHabilitado,_tmpUltimaInteraccion);
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
  public Object buscarProductosPorNombre(final String patron,
      final Continuation<? super List<ProductoEntity>> $completion) {
    final String _sql = "SELECT * FROM productos WHERE habilitado = 1 AND nombre LIKE '%' || ? || '%' ORDER BY nombre ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, patron);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ProductoEntity>>() {
      @Override
      @NonNull
      public List<ProductoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfImagenUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenUrl");
          final int _cursorIndexOfCantidad = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad");
          final int _cursorIndexOfCantidadMinima = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad_minima");
          final int _cursorIndexOfHabilitado = CursorUtil.getColumnIndexOrThrow(_cursor, "habilitado");
          final int _cursorIndexOfUltimaInteraccion = CursorUtil.getColumnIndexOrThrow(_cursor, "ultima_interaccion");
          final List<ProductoEntity> _result = new ArrayList<ProductoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductoEntity _item;
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
            final int _tmpCantidad;
            _tmpCantidad = _cursor.getInt(_cursorIndexOfCantidad);
            final int _tmpCantidadMinima;
            _tmpCantidadMinima = _cursor.getInt(_cursorIndexOfCantidadMinima);
            final boolean _tmpHabilitado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHabilitado);
            _tmpHabilitado = _tmp != 0;
            final long _tmpUltimaInteraccion;
            _tmpUltimaInteraccion = _cursor.getLong(_cursorIndexOfUltimaInteraccion);
            _item = new ProductoEntity(_tmpId,_tmpNombre,_tmpImagenUrl,_tmpCantidad,_tmpCantidadMinima,_tmpHabilitado,_tmpUltimaInteraccion);
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
  public Object obtenerProductoPorId(final int id,
      final Continuation<? super ProductoEntity> $completion) {
    final String _sql = "SELECT * FROM productos WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProductoEntity>() {
      @Override
      @Nullable
      public ProductoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfImagenUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenUrl");
          final int _cursorIndexOfCantidad = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad");
          final int _cursorIndexOfCantidadMinima = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad_minima");
          final int _cursorIndexOfHabilitado = CursorUtil.getColumnIndexOrThrow(_cursor, "habilitado");
          final int _cursorIndexOfUltimaInteraccion = CursorUtil.getColumnIndexOrThrow(_cursor, "ultima_interaccion");
          final ProductoEntity _result;
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
            final int _tmpCantidad;
            _tmpCantidad = _cursor.getInt(_cursorIndexOfCantidad);
            final int _tmpCantidadMinima;
            _tmpCantidadMinima = _cursor.getInt(_cursorIndexOfCantidadMinima);
            final boolean _tmpHabilitado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHabilitado);
            _tmpHabilitado = _tmp != 0;
            final long _tmpUltimaInteraccion;
            _tmpUltimaInteraccion = _cursor.getLong(_cursorIndexOfUltimaInteraccion);
            _result = new ProductoEntity(_tmpId,_tmpNombre,_tmpImagenUrl,_tmpCantidad,_tmpCantidadMinima,_tmpHabilitado,_tmpUltimaInteraccion);
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
