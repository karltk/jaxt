require 'buildr/java/packaging'

repositories.remote << 'http://repo1.maven.org/maven2/'


define 'no.uib.ii.mouldable' do

  project.version = "0.1.1"
  
  define 'no.uib.ii.mouldable.jaxt.runtime' do
    package(:jar, :id => 'jaxt.runtime')
    compile.with \
      project('no.uib.ii.mouldable.jaxt.axioms')
  end

  define 'no.uib.ii.mouldable.jaxt.axioms' do
    package(:jar, :id => 'jaxt.axioms')
    compile.with \
      'junit:junit:jar:4.8.2'
  end
  
end
