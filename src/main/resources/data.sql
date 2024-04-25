insert into perfil (id, nome)
values (1, 'ADM'),
       (2, 'USUARIO')
on conflict (id) do nothing;

insert into usuario (login, senha, perfil_id)
values ('admin', '$2a$08$2dFreuQvrRC.uM4yv/Y46OK13VpWUBUzODfruvFhNfvjB0pWZGhSW', 1)
on conflict (login) do nothing;