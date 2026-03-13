package com.example.controlalmacenapp.model.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/example/controlalmacenapp/model/dao/ProductoDao;", "", "deleteProducto", "", "producto", "Lcom/example/controlalmacenapp/model/entities/ProductoEntity;", "(Lcom/example/controlalmacenapp/model/entities/ProductoEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductos", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertProducto", "", "insertProductoLista", "productos", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProducto", "app_debug"})
public abstract interface ProductoDao {
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertProducto(@org.jetbrains.annotations.NotNull()
    com.example.controlalmacenapp.model.entities.ProductoEntity producto, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertProductoLista(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.controlalmacenapp.model.entities.ProductoEntity> productos, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateProducto(@org.jetbrains.annotations.NotNull()
    com.example.controlalmacenapp.model.entities.ProductoEntity producto, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteProducto(@org.jetbrains.annotations.NotNull()
    com.example.controlalmacenapp.model.entities.ProductoEntity producto, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM ProductoEntity")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProductos(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.controlalmacenapp.model.entities.ProductoEntity>> $completion);
}