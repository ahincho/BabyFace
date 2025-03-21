-- V3 - Create Leaders View
-- V3 - Create Leaders View
CREATE VIEW leaders AS
SELECT
    u.id AS id,
    RANK() OVER (ORDER BY COALESCE(SUM(g.points), 0) DESC, u.username DESC) AS rank,
    u.username AS name,
    COALESCE(SUM(g.points), 0) AS points
FROM users u
    LEFT JOIN games g ON u.id = g.user_id AND DATE(g.created_at) = CURRENT_DATE
    GROUP BY u.id, u.username
    ORDER BY rank;