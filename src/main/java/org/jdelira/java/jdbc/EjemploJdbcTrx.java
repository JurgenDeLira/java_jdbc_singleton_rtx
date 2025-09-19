package org.jdelira.java.jdbc;

import org.jdelira.java.jdbc.models.Categoria;
import org.jdelira.java.jdbc.models.Producto;
import org.jdelira.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.jdelira.java.jdbc.repositorio.Repositorio;
import org.jdelira.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcTrx {
    public static void main(String[] args) {



        try (Connection conn = ConexionBaseDatos.getInstance()) {

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("============ listar ============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============ obtener por id ============");
            System.out.println(repositorio.porId(1L));

            System.out.println("============ insertar nuevo producto ============");
            Producto producto = new Producto();
            producto.setNombre("Teclado IBM mecánico");
            producto.setPrecio(1550);
            producto.setFechaRegistro(new Date());
            Categoria categoria = new Categoria();
            categoria.setId(3L);
            producto.setCategoria(categoria);
            producto.setSku("abcde12345");
            repositorio.guardar(producto);
            System.out.println("Producto guardado con éxito");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============ editar producto ============");
            producto = new Producto();
            producto.setId(5L);
            producto.setNombre("Teclado Corsair k95 mecánico");
            producto.setPrecio(700);
            producto.setSku("abcd123456");
            categoria = new Categoria();
            categoria.setId(2L);
            producto.setCategoria(categoria);
            producto.setFechaRegistro(new Date());
            repositorio.guardar(producto);
            System.out.println("Producto guardado con éxito");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
