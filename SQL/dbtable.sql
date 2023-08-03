-- test.tb_post definition

CREATE TABLE `tb_post` (
                           `post_id` varchar(36) NOT NULL,
                           `POST_TIT` varchar(100) DEFAULT NULL,
                           `POST_CNTT` varchar(1000) DEFAULT NULL,
                           `POST_DTM` datetime DEFAULT NULL,
                           PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.tb_stnd_word definition

CREATE TABLE `tb_stnd_word` (
                                `word_id` varchar(36) DEFAULT NULL,
                                `word_kor_nm` varchar(36) DEFAULT NULL,
                                `word_eng_nm` varchar(100) DEFAULT NULL,
                                `word_abr_nm` varchar(100) DEFAULT NULL,
                                `word_descn` varchar(2000) DEFAULT NULL,
                                `reg_dtm` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.tb_user definition

CREATE TABLE `tb_user` (
                           `USER_ID` varchar(100) DEFAULT NULL,
                           `USER_NM` varchar(100) DEFAULT NULL,
                           `USER_PW` varchar(100) DEFAULT NULL,
                           `USER_TYPE` varchar(5) DEFAULT NULL,
                           `REFRESH_TOKEN` varchar(600) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;