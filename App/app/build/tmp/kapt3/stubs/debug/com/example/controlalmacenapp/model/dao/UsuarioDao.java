package com.example.controlalmacenapp.model.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ \u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0015"}, d2 = {"Lcom/example/controlalmacenapp/model/dao/UsuarioDao;", "", "deleteUsuario", "", "usuario", "Lcom/example/controlalmacenapp/model/entities/UsuarioEntity;", "(Lcom/example/controlalmacenapp/model/entities/UsuarioEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUsuariosHabilitados", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertUsuario", "", "insertUsuarioLista", "usuarios", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginAdmin", "nombre", "", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUsuario", "app_debug"})
@androidx.room.Dao()
public abstract interface UsuarioDao {
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertUsuario(@org.jetbrains.annotations.NotNull()
    com.example.controlalmacenapp.model.entities.UsuarioEntity usuario, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertUsuarioLista(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.controlalmacenapp.model.entities.UsuarioEntity> usuarios, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateUsuario(@org.jetbrains.annotations.NotNull()
    com.example.controlalmacenapp.model.entities.UsuarioEntity usuario, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteUsuario(@org.jetbrains.annotations.NotNull()
    com.example.controlalmacenapp.model.entities.UsuarioEntity usuario, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM usuarios WHERE habilitado = 1 ORDER BY nombre ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUsuariosHabilitados(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.controlalmacenapp.model.entities.UsuarioEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM usuarios WHERE nombre = :nombre AND password = :password AND es_administrador = 1 AND habilitado = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object loginAdmin(@org.jetbrains.annotations.NotNull()
    java.lang.String nombre, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.controlalmacenapp.model.entities.UsuarioEntity> $completion);
}