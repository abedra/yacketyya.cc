set :non_privileged_user, "yacketyyacc"
set :user, ENV["CAP_USER"] || non_privileged_user
ssh_options[:forward_agent] = true 

set :application, "yacketyyacc"
set :repository, "git://github.com/abedra/yacketyya.cc.git"
set :scm, :git
set(:current_branch)     { `git branch`.match(/\* (\S+)\s/m)[1] || raise("Couldn't determine current branch") }
set :branch,             defer { current_branch }
set :use_sudo, false
set :deploy_via, :remote_cache

set :domain, '67.207.136.121'
role :app, domain
role :web, domain
role :db,  domain, :primary => true
set :deploy_to, "~/#{application}"

after "deploy:update", :symlink
after "deploy:restart", "deploy:cleanup"

task :symlink do
  run "ln -nfs #{shared_path}/pids #{current_path}/tmp/pids"
end

namespace :deploy do  
  task :setup, :except => { :no_release => true } do
    dirs = [deploy_to, releases_path, shared_path]
    dirs += %w(system).map { |d| File.join(shared_path, d) }
    run "umask 02 && mkdir -p #{dirs.join(' ')}"
  end
 
  task :migrate do
  end
 
  task :migrations do
  end
 
  task :start do
    run "cd #{current_path} && script/daemon"
  end
 
  task :stop do
    run "cd #{current_path} && if [ -f tmp/pids/daemon.pid ]; then kill `cat tmp/pids/daemon.pid`; fi"
  end
 
  task :restart do
    stop
    start
  end

  task :cold do
    update
    start
  end

  task :tag_update do
    set :tag_name, "deployed_to_#{timestamp_string_without_seconds}"
    `git tag -a -m "Tagging deploy at #{timestamp_string_without_seconds}" #{tag_name} #{branch}`
    `git push --tags`
    puts "Tagged release with #{tag_name}."
  end
end

def timestamp_string_without_seconds
  Time.now.strftime("%Y%m%d%H%M")
end
