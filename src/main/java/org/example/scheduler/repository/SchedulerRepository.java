package org.example.scheduler.repository;

import org.example.scheduler.entity.SchedulerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 저장
 * save(T entity),
 * saveAll(Iterable<T> entities)
 * <br>
 * 조회
 * findById(ID id),
 * findAll(),
 * findAllById(Iterable<ID> ids)
 * <br>
 * 삭제
 * deleteById(ID id),
 * delete(T entity),
 * deleteAll()
 * <br>
 * 기타
 * count(),
 * existsById(ID id)
 */
public interface SchedulerRepository extends JpaRepository<SchedulerEntity,Long>{
}
