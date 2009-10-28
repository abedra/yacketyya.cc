create table urls (
       id                       serial primary key,
       original_url             text not null default '',
       shortened_url            text not null default '',
       created_at               timestamp not null default now()
);
