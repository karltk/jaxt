require 'buildr/java/packaging'

repositories.remote << 'http://repo1.maven.org/maven2/'


define 'no.uib.ii.mouldable' do

  project.version = "0.1.0"
  
  define 'no.uib.ii.mouldable.jaxt.runtime' do
    package(:jar, :id => 'jaxt.runtime')
  end
  
end
