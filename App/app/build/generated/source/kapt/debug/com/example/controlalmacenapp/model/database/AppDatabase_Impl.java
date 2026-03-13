package com.example.controlalmacenapp.model.database;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
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
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class AppDatabase_Impl extends AppDatabase {
  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(1, "bea75d211ff7c98a054349033cc8002f", "5eb33cc1212232bef9bea3786a738aff") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `usuarios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT NOT NULL, `imagenUrl` TEXT, `es_administrador` INTEGER NOT NULL, `password` TEXT, `email` TEXT, `habilitado` INTEGER NOT NULL)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `productos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT NOT NULL, `cantidad` INTEGER NOT NULL, `cantidad_minima` INTEGER NOT NULL, `imagen_url` TEXT, `habilitado` INTEGER NOT NULL)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `proveedores` (`cif` TEXT NOT NULL, `nombre` TEXT NOT NULL, PRIMARY KEY(`cif`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `albaranes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `proveedor_cif` TEXT NOT NULL, `importe` REAL NOT NULL, `pagado` INTEGER NOT NULL, `fecha_pago` INTEGER, `foto_uri` TEXT, FOREIGN KEY(`proveedor_cif`) REFERENCES `proveedores`(`cif`) ON UPDATE NO ACTION ON DELETE RESTRICT )");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_albaranes_proveedor_cif` ON `albaranes` (`proveedor_cif`)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'bea75d211ff7c98a054349033cc8002f')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `usuarios`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `productos`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `proveedores`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `albaranes`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsUsuarios = new HashMap<String, TableInfo.Column>(7);
        _columnsUsuarios.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("imagenUrl", new TableInfo.Column("imagenUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("es_administrador", new TableInfo.Column("es_administrador", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("password", new TableInfo.Column("password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("habilitado", new TableInfo.Column("habilitado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysUsuarios = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesUsuarios = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsuarios = new TableInfo("usuarios", _columnsUsuarios, _foreignKeysUsuarios, _indicesUsuarios);
        final TableInfo _existingUsuarios = TableInfo.read(connection, "usuarios");
        if (!_infoUsuarios.equals(_existingUsuarios)) {
          return new RoomOpenDelegate.ValidationResult(false, "usuarios(com.example.controlalmacenapp.model.entities.UsuarioEntity).\n"
                  + " Expected:\n" + _infoUsuarios + "\n"
                  + " Found:\n" + _existingUsuarios);
        }
        final Map<String, TableInfo.Column> _columnsProductos = new HashMap<String, TableInfo.Column>(6);
        _columnsProductos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("cantidad", new TableInfo.Column("cantidad", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("cantidad_minima", new TableInfo.Column("cantidad_minima", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("imagen_url", new TableInfo.Column("imagen_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductos.put("habilitado", new TableInfo.Column("habilitado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysProductos = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesProductos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProductos = new TableInfo("productos", _columnsProductos, _foreignKeysProductos, _indicesProductos);
        final TableInfo _existingProductos = TableInfo.read(connection, "productos");
        if (!_infoProductos.equals(_existingProductos)) {
          return new RoomOpenDelegate.ValidationResult(false, "productos(com.example.controlalmacenapp.model.entities.ProductoEntity).\n"
                  + " Expected:\n" + _infoProductos + "\n"
                  + " Found:\n" + _existingProductos);
        }
        final Map<String, TableInfo.Column> _columnsProveedores = new HashMap<String, TableInfo.Column>(2);
        _columnsProveedores.put("cif", new TableInfo.Column("cif", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProveedores.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysProveedores = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesProveedores = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProveedores = new TableInfo("proveedores", _columnsProveedores, _foreignKeysProveedores, _indicesProveedores);
        final TableInfo _existingProveedores = TableInfo.read(connection, "proveedores");
        if (!_infoProveedores.equals(_existingProveedores)) {
          return new RoomOpenDelegate.ValidationResult(false, "proveedores(com.example.controlalmacenapp.model.entities.ProveedorEntity).\n"
                  + " Expected:\n" + _infoProveedores + "\n"
                  + " Found:\n" + _existingProveedores);
        }
        final Map<String, TableInfo.Column> _columnsAlbaranes = new HashMap<String, TableInfo.Column>(6);
        _columnsAlbaranes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbaranes.put("proveedor_cif", new TableInfo.Column("proveedor_cif", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbaranes.put("importe", new TableInfo.Column("importe", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbaranes.put("pagado", new TableInfo.Column("pagado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbaranes.put("fecha_pago", new TableInfo.Column("fecha_pago", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbaranes.put("foto_uri", new TableInfo.Column("foto_uri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysAlbaranes = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAlbaranes.add(new TableInfo.ForeignKey("proveedores", "RESTRICT", "NO ACTION", Arrays.asList("proveedor_cif"), Arrays.asList("cif")));
        final Set<TableInfo.Index> _indicesAlbaranes = new HashSet<TableInfo.Index>(1);
        _indicesAlbaranes.add(new TableInfo.Index("index_albaranes_proveedor_cif", false, Arrays.asList("proveedor_cif"), Arrays.asList("ASC")));
        final TableInfo _infoAlbaranes = new TableInfo("albaranes", _columnsAlbaranes, _foreignKeysAlbaranes, _indicesAlbaranes);
        final TableInfo _existingAlbaranes = TableInfo.read(connection, "albaranes");
        if (!_infoAlbaranes.equals(_existingAlbaranes)) {
          return new RoomOpenDelegate.ValidationResult(false, "albaranes(com.example.controlalmacenapp.model.entities.AlbaranEntity).\n"
                  + " Expected:\n" + _infoAlbaranes + "\n"
                  + " Found:\n" + _existingAlbaranes);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "usuarios", "productos", "proveedores", "albaranes");
  }

  @Override
  public void clearAllTables() {
    super.performClear(true, "usuarios", "productos", "albaranes", "proveedores");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }
}
