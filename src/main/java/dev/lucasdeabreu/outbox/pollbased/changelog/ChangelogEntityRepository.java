package dev.lucasdeabreu.outbox.pollbased.changelog;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

interface ChangelogEntityRepository extends Repository<ChangelogEntity, Integer> {

    /*
     https://shekhargulati.com/2022/01/27/correctly-using-postgres-as-queue/
     */
    @Query(value =
        """
        with changelogs_updated as (
            update changelogs
            set status = 'DONE'
            where id IN (
                select id from changelogs c
                where c.status = 'WAITING'
                order by id FOR UPDATE SKIP LOCKED LIMIT ?1)
            RETURNING *)
        select * from changelogs_updated order by id
        """,
      nativeQuery = true)
    List<ChangelogEntity> updateToDoneAndReturnWithLimit(int limit);

}
