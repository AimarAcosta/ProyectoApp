package com.example.controlalmacenapp.model.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.controlalmacenapp.model.dao.ProductoDao;
import com.example.controlalmacenapp.model.dao.ProductoDao_Impl;
import com.example.controlalmacenapp.model.dao.UsuarioDao;
import com.example.controlalmacenapp.model.dao.UsuarioDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UsuarioDao _usuarioDao;

  private volatile ProductoDao _productoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `usuarios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT NOT NULL, `imagenUrl` TEXT, `es_administrador` INTEGER NOT NULL, `password` TEXT, `email` TEXT, `habilitado` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `productos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT NOT NULL, `imagenUrl` TEXT, `cantidad` INTEGER NOT NULL, `cantidad_minima` INTEGER NOT NULL, `habilitado` INTEGER NOT NULL, `ultima_interaccion` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `proveedores` (`cif` TEXT NOT NULL, `nombre` TEXT NOT NULL, PRIMARY KEY(`cif`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `albaranes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `proveedor_cif` TEXT NOT NULL, `importe` REAL NOT NULL, `pagado` INTEGER NOT NULL, `fecha_pago` INTEGER, `foto_uri` TEXT, FOREIGN KEY(`proveedor_cif`) REFERENCES `proveedores`(`cif`) ON UPDATE NO ACTION ON DELETE RESTRICT )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_albaranes_proveedor_cif` ON `albaranes` (`proveedor_cif`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fe36b2fc5f94b3074767e348082acc3d')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `usuarios`");
        db.execSQL("DROP TABLE IF EXISTS `productos`");
        db.execSQL("DROP TABLE IF EXISTS `proveedores`");
        db.execSQL("DROP TABLE IF EXISTS `albaranes`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsuarios = new HashMap<String, TableInfo.Column>(7);
        _columnsUsuarios.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("imagenUrl", new TableInfo.Column("imagenUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("es_administrador", new TableInfo.Column("es_administrador", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("password", new TableInfo.Column("password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("habilitado", new TableInfo.Column("habilitado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsuarios = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsuarios = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsuarios = new TableInfo("usuarios", _columnsUsuarios, _foreignKeysUsuarios, _indicesUsuarios);
        final TableInfo _existingUsuarios = TableInfo.read(db, "usuarios");
        if (!_infoUsuarios.equals(_existingUsuarios)) {
          return new RoomOpenHelper.ValidationResult(false, "usuarios(com.example.controlalmacenapp.model.entities.UsuarioEntity).\n"
                  + " Expected:\n" + _infoUsuarios + "\n"
                  + " Found:\n" + _existingUsuarios);
        }
        final HashMap<String, TableInfo.Column> _columnsProductos = new HashMap<String, TableInfo.Column>(7);
        _columnsProductos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("imagenUrl", new TableInfo.Column("imagenUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("cantidad", new TableInfo.Column("cantidad", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("cantidad_minima", new TableInfo.Column("cantidad_minima", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("habilitado", new TableInfo.Column("habilitado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("ultima_interaccion", new TableInfo.Column("ultima_interaccion", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProductos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProductos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProductos = new TableInfo("productos", _columnsProductos, _foreignKeysProductos, _indicesProductos);
        final TableInfo _existingProductos = TableInfo.read(db, "productos");
        if (!_infoProductos.equals(_existingProductos)) {
          return new RoomOpenHelper.ValidationResult(false, "productos(com.example.controlalmacenapp.model.entities.ProductoEntity).\n"
                  + " Expected:\n" + _infoProductos + "\n"
                  + " Found:\n" + _existingProductos);
        }
        final HashMap<String, TableInfo.Column> _columnsProveedores = new HashMap<String, TableInfo.Column>(2);
        _columnsProveedores.put("cif", new TableInfo.Column("cif", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProveedores.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProveedores = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProveedores = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProveedores = new TableInfo("proveedores", _columnsProveedores, _foreignKeysProveedores, _indicesProveedores);
        final TableInfo _existingProveedores = TableInfo.read(db, "proveedores");
        if (!_infoProveedores.equals(_existingProveedores)) {
          return new RoomOpenHelper.ValidationResult(false, "proveedores(com.example.controlalmacenapp.model.entities.ProveedorEntity).\n"
                  + " Expected:\n" + _infoProveedores + "\n"
                  + " Found:\n" + _existingProveedores);
        }
        final HashMap<String, TableInfo.Column> _columnsAlbaranes = new HashMap<String, TableInfo.Column>(6);
        _columnsAlbaranes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbaranes.put("proveedor_cif", new TableInfo.Column("proveedor_cif", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbaranes.put("importe", new TableInfo.Column("importe", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbaranes.put("pagado", new TableInfo.Column("pagado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbaranes.put("fecha_pago", new TableInfo.Column("fecha_pago", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbaranes.put("foto_uri", new TableInfo.Column("foto_uri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlbaranes = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAlbaranes.add(new TableInfo.ForeignKey("proveedores", "RESTRICT", "NO ACTION", Arrays.asList("proveedor_cif"), Arrays.asList("cif")));
        final HashSet<TableInfo.Index> _indicesAlbaranes = new HashSet<TableInfo.Index>(1);
        _indicesAlbaranes.add(new TableInfo.Index("index_albaranes_proveedor_cif", false, Arrays.asList("proveedor_cif"), Arrays.asList("ASC")));
        final TableInfo _infoAlbaranes = new TableInfo("albaranes", _columnsAlbaranes, _foreignKeysAlbaranes, _indicesAlbaranes);
        final TableInfo _existingAlbaranes = TableInfo.read(db, "albaranes");
        if (!_infoAlbaranes.equals(_existingAlbaranes)) {
          return new RoomOpenHelper.ValidationResult(false, "albaranes(com.example.controlalmacenapp.model.entities.AlbaranEntity).\n"
                  + " Expected:\n" + _infoAlbaranes + "\n"
                  + " Found:\n" + _existingAlbaranes);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "fe36b2fc5f94b3074767e348082acc3d", "9a03fabc32745edd27041bcd8dd0f330");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "usuarios","productos","proveedores","albaranes");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `usuarios`");
      _db.execSQL("DELETE FROM `productos`");
      _db.execSQL("DELETE FROM `albaranes`");
      _db.execSQL("DELETE FROM `proveedores`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UsuarioDao.class, UsuarioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProductoDao.class, ProductoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UsuarioDao usuarioDao() {
    if (_usuarioDao != null) {
      return _usuarioDao;
    } else {
      synchronized(this) {
        if(_usuarioDao == null) {
          _usuarioDao = new UsuarioDao_Impl(this);
        }
        return _usuarioDao;
      }
    }
  }

  @Override
  public ProductoDao productoDao() {
    if (_productoDao != null) {
      return _productoDao;
    } else {
      synchronized(this) {
        if(_productoDao == null) {
          _productoDao = new ProductoDao_Impl(this);
        }
        return _productoDao;
      }
    }
  }
}
