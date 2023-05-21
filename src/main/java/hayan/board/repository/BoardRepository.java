package hayan.board.repository;

import hayan.board.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findTop100ByOrderByCreatedAtDesc();

    List<Board> findTop100ByTitleContainingOrderByCreatedAtDesc(String title);
}
