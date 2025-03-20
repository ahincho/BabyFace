-- V3 - Create Leaders View
CREATE VIEW leaders AS
SELECT
    u.id AS id,
    u.username AS name,
    COALESCE(g.points, 0) AS points,
    RANK() OVER (ORDER BY COALESCE(g.points, 0) DESC, u.username DESC) AS rank
FROM users u LEFT JOIN games g ON u.id = g.user_id
ORDER BY rank;