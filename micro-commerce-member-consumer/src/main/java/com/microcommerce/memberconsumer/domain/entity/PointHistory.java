package com.microcommerce.memberconsumer.domain.entity;

import com.microcommerce.memberconsumer.domain.constant.PointTxType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POINT_HISTORY")
@Entity
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int point;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PointTxType type;

    @Column
    private Timestamp timestamp;


    public static PointHistory getInstance(Long userId, int point, PointTxType type) {
        return PointHistory.builder()
                .userId(userId)
                .point(point)
                .type(type)
                .timestamp(Timestamp.from(Instant.now()))
                .build();
    }

}
