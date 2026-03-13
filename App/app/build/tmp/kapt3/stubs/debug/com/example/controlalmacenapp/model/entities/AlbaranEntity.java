package com.example.controlalmacenapp.model.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u001b\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003JN\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001J\t\u0010%\u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012\u00a8\u0006&"}, d2 = {"Lcom/example/controlalmacenapp/model/entities/AlbaranEntity;", "", "id", "", "proveedorCif", "", "importe", "", "pagado", "", "fechaPago", "", "fotoUri", "(ILjava/lang/String;DZLjava/lang/Long;Ljava/lang/String;)V", "getFechaPago", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFotoUri", "()Ljava/lang/String;", "getId", "()I", "getImporte", "()D", "getPagado", "()Z", "getProveedorCif", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(ILjava/lang/String;DZLjava/lang/Long;Ljava/lang/String;)Lcom/example/controlalmacenapp/model/entities/AlbaranEntity;", "equals", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "albaranes", foreignKeys = {@androidx.room.ForeignKey(entity = com.example.controlalmacenapp.model.entities.ProveedorEntity.class, parentColumns = {"cif"}, childColumns = {"proveedor_cif"}, onDelete = 2)})
public final class AlbaranEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final int id = 0;
    @androidx.room.ColumnInfo(name = "proveedor_cif", index = true)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String proveedorCif = null;
    @androidx.room.ColumnInfo(name = "importe")
    private final double importe = 0.0;
    @androidx.room.ColumnInfo(name = "pagado")
    private final boolean pagado = false;
    @androidx.room.ColumnInfo(name = "fecha_pago")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long fechaPago = null;
    @androidx.room.ColumnInfo(name = "foto_uri")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String fotoUri = null;
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.controlalmacenapp.model.entities.AlbaranEntity copy(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String proveedorCif, double importe, boolean pagado, @org.jetbrains.annotations.Nullable()
    java.lang.Long fechaPago, @org.jetbrains.annotations.Nullable()
    java.lang.String fotoUri) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    public AlbaranEntity(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String proveedorCif, double importe, boolean pagado, @org.jetbrains.annotations.Nullable()
    java.lang.Long fechaPago, @org.jetbrains.annotations.Nullable()
    java.lang.String fotoUri) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProveedorCif() {
        return null;
    }
    
    public final double getImporte() {
        return 0.0;
    }
    
    public final boolean getPagado() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getFechaPago() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFotoUri() {
        return null;
    }
}