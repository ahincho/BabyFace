-- V3 - Create Leaders View
CREATE VIEW leaders AS
SELECT
    u.username AS name,
    COALESCE(SUM(g.hits), 0) AS hits
FROM users u LEFT JOIN games g ON u.id = g.user_id
GROUP BY u.id, u.username
ORDER BY hits DESC;