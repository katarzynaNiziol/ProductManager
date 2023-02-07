package net.codejava;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository repo;

    @Test
    public void testAddNew() {
        Product product = new Product();
        product.setName("Acupressure");
        product.setBrand("1 BRAND");
        product.setTime("60");
        product.setPrice(200);

        Product savedProduct = repo.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Product> products = repo.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);

        for (Product  product : products) {
            System.out.println(product);
        }
    }
    @Test
    public void testUpdate() {
        Integer productId = 13;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product product = optionalProduct.get();

        product.setPrice(120);
        repo.save(product);

        Product updateProduct = repo.findById(productId).get();
        Assertions.assertThat(updateProduct.getPrice()).isEqualTo(120);

    }
        @Test
        public void testGet() {
            Integer productId = 12;
            Optional<Product> optionalProduct = repo.findById(productId);
            Assertions.assertThat(optionalProduct).isPresent();
            System.out.println(optionalProduct.get());
        }

        @Test
        public void testDelete() {
            Integer productId = 17;
            repo.deleteById(productId);

            Optional<Product> optionalProduct = repo.findById(productId);
            Assertions.assertThat(optionalProduct).isNotPresent();


        }
    }

