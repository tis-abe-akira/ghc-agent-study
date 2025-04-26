FIND_INDUSTRY =
SELECT
    *
FROM
    INDUSTRY
WHERE
    $if(industryCode)     {INDUSTRY_CODE = :industryCode}
    AND $if(industryName) {INDUSTRY_NAME LIKE  :%industryName%}
ORDER BY
    INDUSTRY_CODE
