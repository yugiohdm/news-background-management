create table likenews
(
    luid         int           null,
    lusername    varchar(1000) null,
    lnid         int           null,
    lnewstitle   varchar(1000) null,
    lnewscontent varchar(1000) null,
    lnewstype    varchar(1000) null,
    likestate    varchar(1000) null,
    releasedate  varchar(1000) null,
    state        varchar(1000) null,
    lnewimageurl varchar(1000) null,
    constraint lnid
        foreign key (lnid) references releasenews (nid),
    constraint luid
        foreign key (luid) references user (uid)
);

INSERT INTO dxb.likenews (luid, lusername, lnid, lnewstitle, lnewscontent, lnewstype, likestate, releasedate, state, lnewimageurl) VALUES (19, 'cdt', 537062, '古诗词', '待到秋来九月八，我花开后百花杀', '推荐', 'true', '2023-03-09 22:40:04', '审核成功', null);
INSERT INTO dxb.likenews (luid, lusername, lnid, lnewstitle, lnewscontent, lnewstype, likestate, releasedate, state, lnewimageurl) VALUES (19, 'cdt', 312552, '无', '不知道说啥', '推荐', 'true', '2023-05-10 13:10:25', '审核成功', 'cc5a565c72d1431_file_MuMu20221214220339.png');
INSERT INTO dxb.likenews (luid, lusername, lnid, lnewstitle, lnewscontent, lnewstype, likestate, releasedate, state, lnewimageurl) VALUES (1, 'dxb', 262691, '今天吃什么', '今天吃鸡', '推荐', 'true', '2023-05-29 14:32:31', '审核成功', '8f94e412a47d4d8_file_7-14121ZT4063B.png');
