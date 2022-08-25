package nl.suriani.tenniskata.domain;


import nl.suriani.tenniskata.domain.guard.Guard;

import java.util.List;

public record Game(List<Points> points) {
	public Game {
		Guard.isNotNull(points);
		Guard.isNotEmpty(points);

		points = List.copyOf(points);
	}
}
