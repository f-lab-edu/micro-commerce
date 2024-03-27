package com.microcommerce.product.infrastructure.repository;

import com.microcommerce.product.domain.dto.res.ProductResDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.microcommerce.product.domain.entity.QProduct.product;
import static com.microcommerce.product.domain.entity.QProductImage.productImage;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductResDto> getProducts(final List<Long> ids) {
        return jpaQueryFactory.select(
                        Projections.fields(ProductResDto.class,
                                product.id.as("id"),
                                product.name.as("name"),
                                product.price.as("price"),
                                productImage.url.as("representativeImageUrl"),
                                product.sellerId.as("sellerId"),
                                product.sellerName.as("sellerName"),
                                product.stock.as("stock")
                        )
                )
                .from(product)
                .leftJoin(productImage)
                .on(product.id.eq(productImage.productId), productImage.displayOrder.eq(1))
                .where(ids.isEmpty() ? null : product.id.in(ids))
                .fetch();
    }

}
