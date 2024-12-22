DO $$
    DECLARE
        i INT;
        random_name TEXT;
        random_email TEXT;
        random_phone TEXT;
        random_gender TEXT;
        random_hire_date DATE;
        random_job_title TEXT;
        random_department TEXT;
    BEGIN
        FOR i IN 1..1000 LOOP
                -- 랜덤 이름 생성
                random_name := 'User_' || i;

                -- 유니크 이메일 생성
                random_email := 'user_' || i || '@example.com';

                -- 랜덤 전화번호 생성
                random_phone := (100 + FLOOR(RANDOM() * 900))::TEXT || '-' ||
                                (100 + FLOOR(RANDOM() * 900))::TEXT || '-' ||
                                (1000 + FLOOR(RANDOM() * 9000))::TEXT;

                -- 랜덤 성별 생성
                random_gender := (ARRAY['MALE', 'FEMALE', 'OTHER'])[FLOOR(RANDOM() * 3 + 1)::INT];

                -- 랜덤 고용일 생성
                random_hire_date := '2023-01-01'::DATE + (RANDOM() * 365)::INT;

                -- 랜덤 직책 생성
                random_job_title := (ARRAY['Software Engineer', 'Product Manager', 'Designer', 'QA Engineer', 'DevOps Engineer'])[FLOOR(RANDOM() * 5 + 1)::INT];

                -- 랜덤 부서 생성
                random_department := (ARRAY['HR', 'FINANCE', 'IT', 'SALES', 'MARKETING'])[FLOOR(RANDOM() * 5 + 1)::INT];

                -- 데이터 삽입
                INSERT INTO users (name, email, phone_number, gender, hire_date, job_title, department, created_at, updated_at)
                VALUES (random_name, random_email, random_phone, random_gender, random_hire_date, random_job_title, random_department, NOW(), NOW());
            END LOOP;
    END $$;

