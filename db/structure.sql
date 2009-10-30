create table urls (
       id                       serial primary key,
       original_url             text not null default '',
       shortened_url            text not null default '',
       clicks                   integer default 0,
       created_at               timestamp not null default now()
);
